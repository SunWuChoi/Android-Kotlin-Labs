package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_view.*

class MainActivity : AppCompatActivity(), ISongController {
    override fun displaySong() {
        val song = songRepository.getSong(currentSong)
        songName.text = song.name
        songArtist.text = song.artist
        songTrackNum.text = song.trackNumber.toString()
        isAwesomeCb.isChecked = song.isAwesome
    }

    override fun nextSong() {
        if(currentSong < songRepository.getCount() - 1 ) {
            currentSong++
            displaySong()
        }
    }

    override fun prevSong() {
        if(currentSong > 0) {
            currentSong--
            displaySong()
        }
    }

    override fun deleteSong() {
       songRepository.deleteSong(currentSong)
        if(currentSong == songRepository.getCount()){
            currentSong--
        }
        if(songRepository.getCount() > 0) {
            displaySong()
        } else {
            displayEmptyView()
        }
    }

    private fun displayEmptyView() {
        songLayout.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
    }

    override fun toggleAwesome() {
        val oldSong = songRepository.getSong(currentSong)
        val newSong = oldSong.copy(isAwesome = !oldSong.isAwesome)
        songRepository.replaceSong(currentSong, newSong)
        displaySong()
    }

    // 1. an integer to hold the current song index, 2. the song repository
    private var currentSong: Int = 0
    private lateinit var songRepository: ISongRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songRepository = SongRepository()
        displaySong()

        // these listeners should call the respective interface methods
        buttonPrev.setOnClickListener { prevSong() }
        buttonNext.setOnClickListener { nextSong() }
        deleteButton.setOnClickListener { deleteSong() }
        isAwesomeCb.setOnClickListener { toggleAwesome() }
    }
}
