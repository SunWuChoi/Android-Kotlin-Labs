package edu.towson.cosc435.labsapp.interfaces

// We should have a method for every action our controller should take
// the actions our controller should handle are:
// changing to the next song, changing to the previous song, deleting a song,
// marking/unmarking a song as awesome, and displaying the current song

interface ISongController {
    fun displaySong()
    fun nextSong()
    fun prevSong()
    fun deleteSong()
    fun toggleAwesome()
}