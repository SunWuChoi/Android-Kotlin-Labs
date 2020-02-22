package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddSongActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_song)

        // TODO - 8. Add a click listener to the add_song_btn
        // TODO - 9. In the callback, create a new Intent
        // TODO - 10. Create a Song object from the fields in the view
        // TODO - 11. Serialize the Song into a JSON object using GSON and put it into the Intent (intent.putString()...)
        // TODO - 12. Call setResult() with the intent from step 8-9 and RESULT_OK
        // TODO - 13. Call finish() to end the current Activity
    }
}
