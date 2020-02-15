package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// TODO - 6. Make the MainActivity implement the ISongController interface
class MainActivity : AppCompatActivity() {

    // TODO - 7. define some instance variables to hold the state
    // 1. an integer to hold the current song index, 2. the song repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO - 4. create a new file to hold the SongRepository
        // TODO - 5. Create a SongRepository class and implement ISongRepository
        // TODO - 8. initialize the SongRepository
        // TODO - 9. display the current song (song 0)
        // TODO - 10. set up click listeners for the delete button, previous button, next button and checkbox
        // these listeners should call the respective interface methods
        // TODO - 11. Implement all the methods
    }
}
