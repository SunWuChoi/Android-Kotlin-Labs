package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

class MainActivity : AppCompatActivity(), ISongController {
    // TODO - 4. Implement CoroutineScope (use lifecycleScope)
    // TODO - 7. Create methods for hiding and showing spinners (on main thread)


    override fun launchNewSongScreen() {
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_songListFragment_to_addSongFragment)
        }
    }

    override fun addNewSong(song: Song) {
        // TODO - 8. Wrap in try/catch. Run on IO dispatcher
        songs.addSong(song)
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController(R.id.nav_host_fragment)
                .popBackStack()
        } else {
            // update the recyclerview
            recyclerView.adapter?.notifyDataSetChanged()
        }
    }

    override fun deleteSong(idx: Int) {
        val current = songs.getSong(idx)
        // TODO - 9. Wrap in try/catch. Run on IO dispatcher
        songs.remove(current)
    }

    override fun toggleAwesome(idx: Int) {
        val song = songs.getSong(idx)
        val newSong = song.copy(isAwesome = !song.isAwesome)
        // TODO - 10. Wrap in try/catch. Run on IO dispatcher
        songs.replace(idx, newSong)
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

    override fun handleEditedSong(song: Song) {
        songs.replace(editingSongIdx, song)

        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController(R.id.nav_host_fragment)
                .popBackStack()
        } else {
            recyclerView.adapter?.notifyItemChanged(editingSongIdx)
        }

        clearEditingSong()
    }

    override lateinit var songs: ISongRepository
    private var editingSong: Song? = null
    private var editingSongIdx: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songs = SongDatabaseRepository(this)
    }

    // TODO - 5. onStop, cancel any active coroutines
}
