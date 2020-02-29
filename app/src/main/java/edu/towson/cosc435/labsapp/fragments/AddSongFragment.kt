package edu.towson.cosc435.labsapp.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import edu.towson.cosc435.labsapp.R
import edu.towson.cosc435.labsapp.interfaces.ISongController

/**
 * A simple [Fragment] subclass.
 */
class AddSongFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO - 2. Inflate and return the fragment_add_song view.
        return TextView(activity).apply {
            setText(R.string.hello_blank_fragment)
        }
    }

    // TODO - 10. Initialize this property in onAttach by casting the context object
    private lateinit var songController: ISongController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO - 12b. Paste 12a
    }

    // TODO - 11b. Paste handleAddSongClick

    // TODO - 13. Remove Intents in handleAddSongClick and call songController.addNewSong(song) with the new song
}
