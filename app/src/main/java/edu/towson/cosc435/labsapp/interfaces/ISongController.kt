package edu.towson.cosc435.labsapp.interfaces

import edu.towson.cosc435.labsapp.models.Song

// We should have a method for every action our controller should take
// the actions our controller should handle are:
// changing to the next song, changing to the previous song, deleting a song,
// marking/unmarking a song as awesome, and displaying the current song

// TODO - 2. Extend CoroutineScope
interface ISongController {
    // TODO - 3. Make deleteSong, toggleAwesome and addNewSong suspend functions
    fun deleteSong(idx: Int)
    fun toggleAwesome(idx: Int)
    fun launchNewSongScreen()
    val songs: ISongRepository
    fun addNewSong(song: Song)
    fun editSong(idx: Int)
    fun getSongForEdit(): Song?
    fun handleEditedSong(song: Song)
    fun clearEditingSong()
}