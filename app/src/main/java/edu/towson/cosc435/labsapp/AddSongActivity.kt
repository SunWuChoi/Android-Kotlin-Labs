package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_add_song.*
import java.util.*

class AddSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        addSongBtn.setOnClickListener { handleAddSongClick() }
    }

    private fun handleAddSongClick() {
        val intent = Intent()

        val trackNum = try {
            songTrackEt.editableText.toString().toInt()
        } catch (e: Exception) {
            0
        }

        val song = Song(
            id = UUID.randomUUID(),
            name = songNameEt.editableText.toString(),
            artist = songArtistEt.editableText.toString(),
            isAwesome = songIsAwesomeCb.isChecked,
            trackNum = trackNum
        )

        val json: String = Gson().toJson(song)

        intent.putExtra(SONG_KEY, json)

        setResult(Activity.RESULT_OK, intent)

        finish()
    }

    companion object {
        val SONG_KEY = "SONG_EXTRA"
    }
}
