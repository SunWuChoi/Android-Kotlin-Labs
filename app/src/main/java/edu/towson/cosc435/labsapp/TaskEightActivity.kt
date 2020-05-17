package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_task_eight.*

class TaskEightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_eight)

        // TODO - read and display the contents of "final_exam_data.txt

        val db = getDB(this)

        // TODO - add a query for Person # 5 and display the result
    }
}
