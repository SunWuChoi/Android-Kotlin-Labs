package edu.towson.cosc435.labsapp.interfaces

import android.graphics.Bitmap
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.coroutines.CoroutineScope

// We should have a method for every action our controller should take
// the actions our controller should handle are:
// changing to the next song, changing to the previous song, deleting a song,
// marking/unmarking a song as awesome, and displaying the current song

interface ISongController : CoroutineScope {
    suspend fun deleteSong(idx: Int)
    suspend fun toggleAwesome(idx: Int)
    suspend fun addNewSong(song: Song)
    suspend fun handleEditedSong(song: Song)
    suspend fun fetchSongs(): List<Song>
    suspend fun fetchIcon(url: String): Bitmap
    suspend fun checkCache(icon: String): Bitmap?
    suspend fun cacheIcon(filename: String, icon: Bitmap)
    fun launchNewSongScreen()
    val songs: ISongRepository
    fun editSong(idx: Int)
    fun getSongForEdit(): Song?
    fun clearEditingSong()
}