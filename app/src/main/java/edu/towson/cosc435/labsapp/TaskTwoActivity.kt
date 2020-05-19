package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_task_two.*

data class User(val name: String, val age: Int)

interface IController {
    val users: List<User>
    fun deleteAt(pos: Int)
}

class TaskTwoActivity : AppCompatActivity(), IController {

    private val mutableUsers = (0..10).map { i -> User("User $i", i+10) }.toMutableList()
    override val users = mutableUsers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_two)



        recyclerView.adapter = UserAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)


    }

    override fun deleteAt(pos: Int){
        users.removeAt(pos)

    }
}
