package edu.towson.cosc435.labsapp.fragments


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import edu.towson.cosc435.labsapp.R
import edu.towson.cosc435.labsapp.SongsAdapter
import edu.towson.cosc435.labsapp.interfaces.ISongController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_song_list.*
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class SongListFragment : Fragment() {

    private lateinit var songController: ISongController

    override fun onAttach(context: Context) {
        super.onAttach(context)

        when(context) {
            is ISongController -> songController = context
            else -> throw Exception("ISongController expected")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_song_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SongsAdapter(songController)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(context)

        add_song_btn?.setOnClickListener { songController.launchNewSongScreen() }
    }

    override fun onResume() {
        super.onResume()

        songController.launch{
            songController.songs.getAll()
            recyclerView?.adapter?.notifyDataSetChanged()
        }
    }
}
