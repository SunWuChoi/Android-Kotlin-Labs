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
import edu.towson.cosc435.labsapp.fragments.AddSongFragment
import edu.towson.cosc435.labsapp.fragments.SongListFragment
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_song_list.*

class MainActivity : AppCompatActivity(), ISongController {

    override fun launchNewSongScreen() {
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_songListFragment_to_addSongFragment)
        }
    }

    override fun addNewSong(song: Song) {
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
        songs.remove(current)
    }

    override fun toggleAwesome(idx: Int) {
        val song = songs.getSong(idx)
        val newSong = song.copy(isAwesome = !song.isAwesome)
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

        // TODO - 5. replace with your Database repository
        songs = SongRepository()
    }
}
