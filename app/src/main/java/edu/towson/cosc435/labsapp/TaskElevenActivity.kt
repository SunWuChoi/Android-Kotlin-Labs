package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_task_eleven.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TaskElevenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_eleven)

        GlobalScope.launch(Dispatchers.IO) {
            updateTextView()
        }
    }

    suspend fun updateTextView() {
        delay(2000)
        taskElevenTextView.text = "DONE!"
    }
}
