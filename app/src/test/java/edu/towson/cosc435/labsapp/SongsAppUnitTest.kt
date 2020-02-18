package edu.towson.cosc435.labsapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SongsAppUnitTest {
    @Test
    fun songs_repo_replace_works() {
        val songs = SongRepository()

        val song = songs.getSong(0)
        assertEquals("Song0",song.name)

        songs.replaceSong(0,song.copy("TEST"))

        val song2 = songs.getSong(0)

        assertEquals("TEST", song2.name)
    }
}
