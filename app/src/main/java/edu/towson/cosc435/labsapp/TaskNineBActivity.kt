package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_task_nine_b.*

class TaskNineBActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_nine_b)
    }

    companion object {
        val EXTRA_KEY = "extra_key"
    }
}
