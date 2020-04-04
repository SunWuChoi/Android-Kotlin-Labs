package edu.towson.cosc435.labsapp.database

import android.content.Context
import androidx.room.Room
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.coroutines.delay
import java.lang.Exception

class SongDatabaseRepository(ctx: Context) : ISongRepository {
    // TODO - 17. Add 2000 ms delay to all methods and run on IO dispatcher
    private val songList: MutableList<Song> = mutableListOf()
    private val db: SongDatabase

    init {
        db = Room.databaseBuilder(
            ctx,
            SongDatabase::class.java,
            "songs.db"
        ).allowMainThreadQueries().build()
        // TODO - 15. Remove allowMainThreadQueries
        // TODO - 16. Lazily fill the song list (can't refresh here)
        refreshSongList()
    }

    override fun getCount(): Int {
        return songList.size
    }

    override fun getSong(idx: Int): Song {
        return songList.get(idx)
    }

    override fun getAll(): List<Song> {
        return songList
    }

    override fun remove(song: Song) {
        // this will throw an exception randomly
        if (System.currentTimeMillis() % 2 == 0L) throw Exception()
        db.songDao().deleteSong(song)
        refreshSongList()
    }

    override fun replace(idx: Int, song: Song) {
        // this will throw an exception randomly
        if (System.currentTimeMillis() % 2 == 0L) throw Exception()
        db.songDao().updateSong(song)
        refreshSongList()
    }

    override fun addSong(song: Song) {
        // this will throw an exception randomly
        if (System.currentTimeMillis() % 2 == 0L) throw Exception()
        db.songDao().addSong(song)
        refreshSongList()
    }

    private fun refreshSongList() {
        songList.clear()
        val songs = db.songDao().getAllSongs()
        songList.addAll(songs)
    }
}