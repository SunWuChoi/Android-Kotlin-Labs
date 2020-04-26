package edu.towson.cosc435.labsapp

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import edu.towson.cosc431.contentproviderdemo.PersonContract
import edu.towson.cosc435.contentproviderdemo.adapters.PersonAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IController {

    private val mutablePeople: MutableList<Person> = mutableListOf()
    override val people = mutablePeople

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PersonAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        readContentProvider()

        pick_person_btn.setOnClickListener {
            // TODO - 3. launch the picker activity
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        // TODO - 4. Handle the picker result
                    }
                }
            }
        }
    }

    fun readContentProvider() {
        // TODO - 1. query the content provider and populate the person list
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
        val REQUEST_CODE = 1
    }
}

interface IController {
    val people: List<Person>
}
