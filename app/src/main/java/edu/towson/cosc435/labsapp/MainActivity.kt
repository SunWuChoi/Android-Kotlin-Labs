package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ISongController {

    override fun launchNewSongScreen() {
        // TODO - 6. Replace the contents of this method.
        // Use the SupportFragmentManager to replace the fragment_container with the AddSongFragment
        val intent = Intent(this, AddSongActivity::class.java)
        startActivityForResult(intent, ADD_SONG_REQUEST_CODE)
    }

    // TODO - 8. Implement addNewSong(song: Song) - First, add the song to the repository, then use the SupportFragmentManager to replace the fragment_container with the SongListFragment

    override fun deleteSong(idx: Int) {
        val current = songs.getSong(idx)
        songs.remove(current)
    }

    override fun toggleAwesome(idx: Int) {
        val song = songs.getSong(idx)
        val newSong = song.copy(isAwesome = !song.isAwesome)
        songs.replace(idx, newSong)
    }

    override lateinit var songs: ISongRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songs = SongRepository()

        // TODO - 5. Use the SupportFragmentManager to add the SongListFragment to your layout

        // TODO - 3a. Move the rest of the code in this method into SongListFragment.onViewCreated (override)
        val adapter = SongsAdapter(this)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        add_song_btn.setOnClickListener { launchNewSongScreen() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            ADD_SONG_REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        // handle the result
                        val json = data?.getStringExtra(AddSongActivity.SONG_KEY)

                        val song = Gson().fromJson(json, Song::class.java)

                        songs.addSong(song)
                    }
                    Activity.RESULT_CANCELED -> {
                        Toast.makeText(this, "Use cancelled", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    companion object {
        val ADD_SONG_REQUEST_CODE = 1
    }
}
