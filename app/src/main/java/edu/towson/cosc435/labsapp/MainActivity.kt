package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_view.*
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity(), ISongController {

    // TODO - 14. (OPTIONAL) implement editSong(idx: Int)

    override fun launchNewSongScreen() {
        val intent = Intent(this, AddSongActivity::class.java)
        startActivityForResult(intent, ADD_SONG_REQUEST_CODE)
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

    fun displaySong(song: Song) {
        songName.text = song.name
        songArtist.text = song.artist
        songTrackNum.text = song.trackNum.toString()
        isAwesomeCb.isChecked = song.isAwesome
    }

    override lateinit var songs: ISongRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songs = SongRepository()

        val adapter = SongsAdapter(this)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        add_song_btn.setOnClickListener { launchNewSongScreen() }

        // TODO - 11. Update main_activity.xml to use RecyclerView
        // TODO - 12. Instantiate the recycler view adapter in onCreate and set it on the RecyclerView
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
