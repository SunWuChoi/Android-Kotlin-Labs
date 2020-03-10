package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.fragments.AddSongFragment
import edu.towson.cosc435.labsapp.fragments.SongListFragment
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ISongController {

    override fun launchNewSongScreen() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AddSongFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun addNewSong(song: Song) {
        songs.addSong(song)
        supportFragmentManager
            .popBackStack()
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

    override lateinit var songs: ISongRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songs = SongRepository()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, SongListFragment())
            .commit()
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
