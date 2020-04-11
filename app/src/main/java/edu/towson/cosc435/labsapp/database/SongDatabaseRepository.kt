package edu.towson.cosc435.labsapp.database

import android.content.Context
import androidx.room.Room
import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.coroutines.delay
import java.lang.Exception

class SongDatabaseRepository(ctx: Context) : ISongRepository {
    private val songList: MutableList<Song> = mutableListOf()
    private val db: SongDatabase

    init {
        db = Room.databaseBuilder(
            ctx,
            SongDatabase::class.java,
            "songs.db"
        ).build()
    }

    override fun getCount(): Int {
        return songList.size
    }

    override fun getSong(idx: Int): Song {
        return songList.get(idx)
    }

    override suspend fun getAll(): List<Song> {
        if(songList.size == 0) {
            refreshSongList()
        }
        return songList
    }

    override suspend fun remove(song: Song) {
        delay(2000)
        // this will throw an exception randomly
        if (System.currentTimeMillis() % 2 == 0L) throw Exception()
        db.songDao().deleteSong(song)
        refreshSongList()
    }

    override suspend fun replace(idx: Int, song: Song) {
        delay(2000)
        // this will throw an exception randomly
        if (System.currentTimeMillis() % 2 == 0L) throw Exception()
        db.songDao().updateSong(song)
        refreshSongList()
    }

    override suspend fun addSong(song: Song) {
        delay(2000)
        // this will throw an exception randomly
        if (System.currentTimeMillis() % 2 == 0L) throw Exception()
        db.songDao().addSong(song)
        refreshSongList()
    }

    private suspend fun refreshSongList() {
        songList.clear()
        val songs = db.songDao().getAllSongs()
        songList.addAll(songs)
    }
}