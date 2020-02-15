package edu.towson.cosc435.labsapp.interfaces

// TODO - 2. define an interface the our Controller should conform to.
// We should have a method for every action our controller should take
// the actions our controller should handle are:
// changing to the next song, changing to the previous song, deleting a song,
// marking/unmarking a song as awesome, and displaying the current song

interface ISongController {
    fun displaySong()
}