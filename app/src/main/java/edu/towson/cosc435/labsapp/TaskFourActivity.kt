package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_task_four.*

class TaskFourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_four)

        FragmentButton.setOnClickListener {
            findNavController(R.id.fragment_container)
                .navigate(R.id.action_taskFourFragment_to_secondFragment)

        }





    }
}
