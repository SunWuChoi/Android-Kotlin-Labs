package edu.towson.cosc435.labsapp

import android.app.Activity
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.database.SongDatabaseRepository
import edu.towson.cosc435.labsapp.fragments.AddSongFragment
import edu.towson.cosc435.labsapp.fragments.SongListFragment
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import edu.towson.cosc435.labsapp.network.ISongApi
import edu.towson.cosc435.labsapp.network.SongApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), ISongController {
    private fun showSpinner() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideSpinner() {
        progressBar.visibility = View.GONE
    }

    override fun launchNewSongScreen() {
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_songListFragment_to_addSongFragment)
        }
    }

    override suspend fun addNewSong(song: Song) {
        showSpinner()
        try {
            withContext(Dispatchers.IO) {
                songs.addSong(song)
            }
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                findNavController(R.id.nav_host_fragment)
                    .popBackStack()
            } else {
                // update the recyclerview
                recyclerView.adapter?.notifyDataSetChanged()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
            Toast.makeText(this, "Failed to add new song", Toast.LENGTH_SHORT).show()
            throw e
        } finally {
            hideSpinner()
        }
    }

    override suspend fun deleteSong(idx: Int) {
        showSpinner()
        val current = songs.getSong(idx)
        try {
            withContext(Dispatchers.IO) {
                songs.remove(current)
            }
        } catch(e: Exception) {
            Log.e(TAG, "Error ${e.message}")
            Toast.makeText(this, "Failed to delete song", Toast.LENGTH_SHORT).show()
            throw e
        } finally {
            hideSpinner()
        }
    }

    override suspend fun toggleAwesome(idx: Int) {
        showSpinner()
        val song = songs.getSong(idx)
        val newSong = song.copy(isAwesome = !song.isAwesome)
        try {
            songs.replace(idx, newSong)
        } catch(e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
            Toast.makeText(this, "Failed to update song", Toast.LENGTH_SHORT).show()
            throw e
        } finally {
            hideSpinner()
        }
    }

    override fun editSong(idx: Int) {
        val song = songs.getSong(idx)
        editingSong = song
        editingSongIdx = idx

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_songListFragment_to_addSongFragment)
        } else {
            // landscape
            (addSongFragment as AddSongFragment).populateSong()
        }
    }

    override fun getSongForEdit(): Song? {
        return editingSong
    }

    override fun clearEditingSong() {
        editingSong = null
        editingSongIdx = -1
    }

    override val coroutineContext: CoroutineContext
        get() = lifecycleScope.coroutineContext

    override suspend fun handleEditedSong(song: Song) {
        showSpinner()
        try {
            songs.replace(editingSongIdx, song)
            if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                findNavController(R.id.nav_host_fragment)
                    .popBackStack()
            } else {
                recyclerView.adapter?.notifyItemChanged(editingSongIdx)
            }
            clearEditingSong()
        } catch(e: Exception){
            Log.e(TAG, "Error: ${e.message}")
            Toast.makeText(this, "Failed to update song", Toast.LENGTH_SHORT).show()
            throw e
        } finally {
            hideSpinner()
        }
    }

    override suspend fun fetchSongs(): List<Song> {
        return songApi.fetchSongs().await()
    }

    override suspend fun fetchIcon(url: String): Bitmap {
        return songApi.fetchIcon(url).await()
    }

    override suspend fun checkCache(icon: String): Bitmap? {
        val file = File(cacheDir, icon)
        if(file.exists()) {
            val input = file.inputStream()
            return BitmapFactory.decodeStream(input)
        } else {
            return null
        }
    }

    override suspend fun cacheIcon(filename: String, icon: Bitmap) {
        val file = File(cacheDir, filename)
        val output = file.outputStream()
        icon.compress(Bitmap.CompressFormat.JPEG, 100, output)
    }

    override lateinit var songs: ISongRepository
    private var editingSong: Song? = null
    private var editingSongIdx: Int = -1
    private lateinit var songApi: ISongApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songApi = SongApi(this)

        songs = SongDatabaseRepository(this)

        launch {
            val songListAPI = fetchSongs()
            val songListDB = songs.getAll()
            songListAPI.forEach { songAPI ->
                if(songListDB.firstOrNull { songDB -> songDB.id == songAPI.id } == null) {
                    songs.addSong(songAPI)
                }
            }
        }

        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val jobInfo = JobInfo.Builder(JOB_ID, ComponentName(this, SongsService::class.java))
        jobInfo.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        jobInfo.setMinimumLatency(15* 1000)
        scheduler.schedule(jobInfo.build())


        MessageQueue.Channel.observe(this, { song ->
            Log.d(TAG, "Recieved new song from SongsService: $song")
            NotificationManagerCompat.from(this).cancel(SongsService.NOTIF_ID)
            launch(Dispatchers.IO) {
                (songs as SongDatabaseRepository).refreshSongList()
                withContext(Dispatchers.Main) {
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }
        })

    }

    override fun onStop() {
        super.onStop()
        this.cancel()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
        val JOB_ID = 1
    }
    
    // TODO - 7. Subclass Application and implement Application.ActivityLifecycleCallbacks
    // TODO - 8. Using a LiveData object, only refresh the list if the MainActivity is visible
}
