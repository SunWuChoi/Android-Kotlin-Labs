package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_task_eight.*
import java.io.File
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.InputStream


class TaskEightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_eight)

        // TODO - read and display the contents of "final_exam_data.txt

        //var text = readFileAsLinesUsingBufferedReader()

        val inputStream: InputStream = File("raw/final_exam_data.txt").inputStream()

        val inputString = inputStream.bufferedReader().use { it.readText() }
        println(inputString)


        Task8TextView.text = inputString
        val db = getDB(this)

        // TODO - add a query for Person # 5 and display the result

        //db.personDao().insert()
    }
    fun readFileAsLinesUsingBufferedReader(fileName: String): List<String>
            = File(fileName).bufferedReader().readLines()

    fun readFileLineByLineUsingForEachLine(fileName: String){
        return File(fileName).forEachLine { println(it) }
    }

}
