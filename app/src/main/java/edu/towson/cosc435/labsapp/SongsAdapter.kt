package edu.towson.cosc435.labsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.labsapp.interfaces.ISongController
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.android.synthetic.main.song_view.view.*

class SongsAdapter(private val controller: ISongController) : RecyclerView.Adapter<SongViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_view, parent, false)
        val viewHolder = SongViewHolder(view)

        view.deleteButton.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.deleteSong(position)
            this.notifyItemRemoved(position)
        }
        view.isAwesomeCb.setOnClickListener {
            val position = viewHolder.adapterPosition
            controller.toggleAwesome(position)
            this.notifyItemChanged(position)
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
        holder.bindSong(song)
    }
}

class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindSong(song: Song) {
        itemView.songName.text = song.name
        itemView.songArtist.text = song.artist
        itemView.songTrackNum.text = song.trackNum.toString()
        itemView.isAwesomeCb.isChecked = song.isAwesome
    }
}