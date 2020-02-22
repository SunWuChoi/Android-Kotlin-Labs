package edu.towson.cosc431.labsapp

import edu.towson.cosc431.labsapp.interfaces.ISongRepository
import edu.towson.cosc431.labsapp.models.Song

class SongRepository : ISongRepository {
    private var songs: MutableList<Song> = mutableListOf()

    init {
        val seed = (1..10).map { idx -> Song("Song${idx}", "Artist${idx}", idx, idx % 2 == 0) }
        songs.addAll(seed)
    }

    override fun getCount(): Int {
        return songs.size
    }

    override fun getSong(idx: Int): Song {
        return songs.get(idx)
    }

    override fun getAll(): List<Song> {
        return songs
    }

    override fun remove(song: Song) {
        songs.remove(song)
    }

    override fun replace(idx: Int, song: Song) {
        if(idx >= songs.size) throw Exception("Outside of bounds")
        songs[idx] = song
    }
}