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
    fun launchNewSongScreen()
    // TODO - 1. remove nextSong, prevSong and displaySong
    // TODO - 2. add an idx:Int parameter to deleteSong and toggleAwesome
    // TODO - 3. Add a val songs: ISongRespository property
    // TODO - 13. (OPTIONAL) add editSong(idx: Int)
}