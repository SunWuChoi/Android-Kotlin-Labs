package edu.towson.cosc435.labsapp.interfaces

import edu.towson.cosc435.labsapp.models.Song

// We should have a method for every action our controller should take
// the actions our controller should handle are:
// changing to the next song, changing to the previous song, deleting a song,
// marking/unmarking a song as awesome, and displaying the current song

interface ISongController {
    fun nextSong()
    fun prevSong()
    fun deleteSong()
    fun toggleAwesome()
    fun displaySong(song: Song)
    fun launchAddSongScreen()
}