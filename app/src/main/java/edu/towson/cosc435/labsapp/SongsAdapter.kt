package edu.towson.cosc435.labsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.song_view.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongsAdapter(private val controller: ISongController) : RecyclerView.Adapter<SongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_view, parent, false)
        val viewHolder = SongViewHolder(view)

        view.deleteButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.launch {
                try {
                    view.alpha = 0.5f
                    controller.deleteSong(position)
                    this@SongsAdapter.notifyItemRemoved(position)
                } catch (e: Exception) {
                    view.alpha = 1.0f
                }
            }
        }
        view.isAwesomeCb.setOnClickListener {
            val position = viewHolder.adapterPosition
            view.alpha = 0.5f
            controller.launch {
                try {
                    controller.toggleAwesome(position)
                } catch (e: Exception) {

                } finally {
                    view.alpha = 1.0f
                    this@SongsAdapter.notifyItemChanged(position)
                }
            }
        }
        view.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.editSong(position)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return controller.songs.getCount()
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = controller.songs.getSong(position)
        holder.bindSong(controller, song)
    }
}

class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindSong(controller: ISongController, song: Song) {
        itemView.songName.text = song.name
        itemView.songArtist.text = song.artist
        itemView.songTrackNum.text = song.trackNum.toString()
        itemView.isAwesomeCb.isChecked = song.isAwesome
        itemView.iconProgress.visibility = View.VISIBLE
        itemView.songIconImage.visibility = View.INVISIBLE
        controller.launch(Dispatchers.Main) {
            val bitmap = controller.fetchIcon(song.iconUrl)
            itemView.songIconImage.setImageBitmap(bitmap)
            itemView.iconProgress.visibility = View.INVISIBLE
            itemView.songIconImage.visibility = View.VISIBLE
        }
    }
}