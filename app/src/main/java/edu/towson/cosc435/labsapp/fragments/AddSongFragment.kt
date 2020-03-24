package edu.towson.cosc435.labsapp.fragments


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import edu.towson.cosc435.labsapp.AddSongActivity
import edu.towson.cosc435.labsapp.R
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.fragment_add_song.*
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class AddSongFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_song, container, false)
    }

    private lateinit var songController: ISongController

    override fun onAttach(context: Context) {
        super.onAttach(context)

        when(context) {
            is ISongController -> songController = context
            else -> throw Exception("ISongController expected")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addSongBtn.setOnClickListener { handleAddSongClick() }



        val song = songController.getSongForEdit()

        populateSongForm(song)



    }

    private fun populateSongForm(song: Song?) {
        if(song != null) {
            clearForm()
            songNameEt.editableText.insert(0, song.name)
            songArtistEt.editableText.insert(0, song.artist)
            songTrackEt.editableText.insert(0, song.trackNum.toString())
            songIsAwesomeCb.isChecked = song.isAwesome
            addSongTitle?.text = "Edit Song"
            addSongBtn.text = "Edit Song"
        } else {
            addSongTitle?.text = resources.getString(R.string.add_song_btn_txt)
            addSongBtn.text = resources.getString(R.string.add_song_btn_txt)
        }
    }


    private fun clearForm(){
        songNameEt.editableText.clear()
        songArtistEt.editableText.clear()
        songTrackEt.editableText.clear()
        songIsAwesomeCb.isChecked = false
    }

    private fun handleAddSongClick() {
        val trackNum = try {
            songTrackEt.editableText.toString().toInt()
        } catch (e: Exception) {
            0
        }

        val song = Song(
            name = songNameEt.editableText.toString(),
            artist = songArtistEt.editableText.toString(),
            isAwesome = songIsAwesomeCb.isChecked,
            trackNum = trackNum
        )

        if(songController.getSongForEdit() == null){
            songController.addNewSong(song)
        }else {
            songController.handleEditedSong(song)
            addSongBtn.text = resources.getText(R.string.add_song_btn_txt)
        }

        clearForm()

    }

    fun populateSong() {
        val song = songController.getSongForEdit()
        populateSongForm(song)
    }

}
