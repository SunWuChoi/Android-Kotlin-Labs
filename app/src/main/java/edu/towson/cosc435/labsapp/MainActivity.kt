package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.database.SongDatabaseRepository
import edu.towson.cosc435.labsapp.fragments.AddSongFragment
import edu.towson.cosc435.labsapp.fragments.SongListFragment
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.withContext
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

    override lateinit var songs: ISongRepository
    private var editingSong: Song? = null
    private var editingSongIdx: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songs = SongDatabaseRepository(this)

        // TODO - 7. Make the call to get the list of songs. If a song doesn't exist in your db, add it.
    }

    override fun onStop() {
        super.onStop()
        this.cancel()
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

    // TODO - 1. Create a new class for fetching songs from https://my-json-server.typicode.com/rvalis-towson/lab_api/songs
    // TODO - 2. Create a method for fetching and deserializing the Song list
    // TODO - 3. Create a method for fetching a single song's icon
    // TODO - 4. Add support methods for caching the icon image and retrieving the image
}
