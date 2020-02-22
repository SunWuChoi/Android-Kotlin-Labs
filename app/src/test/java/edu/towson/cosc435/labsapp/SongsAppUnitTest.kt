package edu.towson.cosc431.labsapp

import edu.towson.cosc431.labsapp.models.Song
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SongsAppUnitTest {
    @Test
    fun repo_replace_works() {
        val songs = SongRepository()
        val song = Song("Test1", "", 1, false)
        songs.replace(0, song)

        val replaced = songs.getSong(0)

        assertEquals(replaced.name, song.name)
    }
}
