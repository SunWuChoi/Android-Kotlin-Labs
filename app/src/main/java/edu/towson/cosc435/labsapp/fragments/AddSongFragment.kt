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
import kotlinx.android.synthetic.main.fragment_add_song.addSongBtn
import kotlinx.android.synthetic.main.fragment_add_song.songArtistEt
import kotlinx.android.synthetic.main.fragment_add_song.songIsAwesomeCb
import kotlinx.android.synthetic.main.fragment_add_song.songNameEt
import kotlinx.android.synthetic.main.fragment_add_song.songTrackEt
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

        // TODO - 9. Determine editing state
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

        // TODO - 10. Handle edit
        songController.addNewSong(song)
    }
}
