package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_task_nine.*
import kotlinx.android.synthetic.main.activity_task_nine_b.*

class TaskNineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_nine)

        taskNineFinishBtn.setOnClickListener {
            var text = taskNineEditText
            Log.d(MainActivity::class.java.simpleName, text.toString())
        }


    }
}
