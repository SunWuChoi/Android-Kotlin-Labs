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
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = PersonContract.Person.CONTENT_TYPE
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_CODE -> {
                when(resultCode) {
                    Activity.RESULT_OK -> {
                        val name = data?.getStringExtra(PersonContract.PERSON_NAME_EXTRA)
                        picker_result_tv.text = name
                    }
                }
            }
        }
    }

    fun readContentProvider() {
        val contentResolver = getContentResolver()
        val uri = PersonContract.Person.CONTENT_URI
        val proj = PersonContract.Person.PROJECTION_ALL
        val sort = PersonContract.Person.SORT_ORDER_DEFAULT
        val cursor = contentResolver.query(uri, proj, "", arrayOf<String>(), sort )
            if(cursor != null) {
                if(cursor.count > 0){
                    while(cursor.moveToNext()){
                        val name = cursor.getString(cursor.getColumnIndex(PersonContract.Person.NAME))
                        val age = cursor.getInt(cursor.getColumnIndex(PersonContract.Person.AGE))
                        val person = Person(name,age)
                        mutablePeople.add(person)
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                cursor.close()
            }
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
        val REQUEST_CODE = 1
    }
}

interface IController {
    val people: List<Person>
}
