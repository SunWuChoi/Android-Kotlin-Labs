package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.activity_add_song.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.song_view.*
import java.lang.Exception

class AddSongActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        addSongBtn.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.addSongBtn -> {
                val intent = Intent()
                val name = songNameEt.editableText.toString()
                val artist = songArtistEt.editableText.toString()
                val track = songTrackEt.editableText.toString()
                val isAwesome = songIsAwesomeCb.isChecked
                try{
                    val trackNum = track.toInt()
                    val song = Song(name, artist, trackNum, isAwesome)
                    val json = Gson().toJson(song)
                    intent.putExtra(SONG_EXTRA_KEY,json)
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }catch(ex: Exception){
                    Toast.makeText(this,getString(R.string.invalid_track_msg),Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    companion object{
        val SONG_EXTRA_KEY = "SONG"
    }

}
