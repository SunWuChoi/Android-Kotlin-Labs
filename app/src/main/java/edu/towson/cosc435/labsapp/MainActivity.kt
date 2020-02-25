package edu.towson.cosc431.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import edu.towson.cosc431.labsapp.interfaces.ISongController
import edu.towson.cosc431.labsapp.interfaces.ISongRepository
import edu.towson.cosc431.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_view.*

class MainActivity : AppCompatActivity(), ISongController {
    override fun launchNewSongScreen() {

    }



    // TODO - 5. Commit your work. "git add ." then "git commit -m 'Finished lab5 part1'
    // TODO - 6. Do "git checkout lab5_part2" then continue with the next set of instructions

    override fun nextSong() {
        if(currentSongIndex >= songs.getCount() - 1) return
        currentSongIndex++
        displaySong()
    }

    override fun prevSong() {
        if(currentSongIndex <= 0) return
        currentSongIndex--
        displaySong()
    }

    override fun deleteSong() {
        val current = songs.getSong(currentSongIndex)
        songs.remove(current)
        if(currentSongIndex >= songs.getCount()) {
            currentSongIndex = songs.getCount() - 1
        }
        if(songs.getCount() == 0) displayNoSongs()
        else displaySong()
    }

    override fun toggleAwesome() {
        val song = songs.getSong(currentSongIndex)
        val newSong = song.copy(isAwesome = !song.isAwesome)
        songs.replace(currentSongIndex, newSong)
    }

    override fun displaySong(song: Song) {
        songName.text = song.name
        songArtist.text = song.artist
        songTrackNum.text = song.trackNum.toString()
        isAwesomeCb.isChecked = song.isAwesome
    }

    lateinit var songs: ISongRepository
    var currentSongIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songs = SongRepository()
        displaySong()
        buttonNext.setOnClickListener { nextSong() }
        buttonPrev.setOnClickListener { prevSong() }
        deleteButton.setOnClickListener { deleteSong() }
        isAwesomeCb.setOnClickListener { toggleAwesome() }
        // TODO - 4. Add an onClickListener that calls the launch new song method
    }

    private fun displaySong() {
        val song = songs.getSong(currentSongIndex)
        displaySong(song)
    }

    private fun displayNoSongs() {
        songLayout.visibility = View.GONE
        buttonPanel.visibility = View.GONE
        emptyView.visibility = View.VISIBLE
    }
}
