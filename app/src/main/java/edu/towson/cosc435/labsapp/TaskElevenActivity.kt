package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_task_eleven.*
import kotlinx.coroutines.*
import java.lang.Exception

class TaskElevenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_eleven)

        lifecycleScope.launch {
            try {
                withContext(Dispatchers.Main){
                    updateTextView()
                }
            }catch (e : Exception){

            }

        }
    }

    suspend fun updateTextView() {
        delay(2000)
        taskElevenTextView.text = "DONE!"
    }
}
