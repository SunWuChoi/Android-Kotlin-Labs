package edu.towson.cosc435.labsapp

import edu.towson.cosc435.labsapp.interfaces.ISongRepository
import edu.towson.cosc435.labsapp.models.Song

class SongRepository : ISongRepository {

    private val songs: MutableList<Song> = mutableListOf()

    init {
        songs.addAll((0..10).map { idx -> Song("Song${idx}","Artist${idx}","Albums${idx}", idx, idx % 2 == 0)})
    }

    override fun getCount(): Int {
        return songs.size
    }

    override fun getSong(idx: Int): Song {
        if(idx >= songs.size || idx < 0) throw Exception("Index out of bounds")
        return songs[idx]
    }

    override fun getSongs(): List<Song> {
        return songs
    }

    override fun deleteSong(idx: Int) {
        if(idx >= songs.size || idx < 0) throw Exception("Index out of bounds")
        songs.removeAt(idx)
    }

    override fun replaceSong(idx: Int, song: Song) {
        if(idx >= songs.size || idx < 0) throw Exception("Index out of bounds")
        songs.set(idx, song)
    }

}