package edu.towson.cosc435.labsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtons()
    }

    private fun setupButtons() {
        launchTaskTwoBtn.setOnClickListener(this)
        launchTaskThreeBtn.setOnClickListener(this)
        launchTaskFourBtn.setOnClickListener(this)
        launchTaskFiveBtn.setOnClickListener(this)
        launchTaskSixBtn.setOnClickListener(this)
        launchTaskSevenBtn.setOnClickListener(this)
        launchTaskEightBtn.setOnClickListener(this)
        launchTaskNineBtn.setOnClickListener(this)
        launchTaskTenBtn.setOnClickListener(this)
        launchTaskElevenBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view!!.id) {
            R.id.launchTaskTwoBtn -> {
                val intent = Intent(this, TaskTwoActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskThreeBtn -> {
                val intent = Intent(this, TaskThreeActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskFourBtn -> {
                val intent = Intent(this, TaskFourActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskFiveBtn -> {
                val intent = Intent(this, TaskFiveActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskSixBtn -> {
                val intent = Intent(this, TaskSixActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskSevenBtn -> {
                val intent = Intent(this, TaskSevenActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskEightBtn -> {
                val intent = Intent(this, TaskEightActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskNineBtn -> {
                val intent = Intent(this, TaskNineActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskTenBtn -> {
                val intent = Intent(this, TaskTwoActivity::class.java)
                startActivity(intent)
            }
            R.id.launchTaskElevenBtn -> {
                val intent = Intent(this, TaskElevenActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

// TODO - 1. Create a new Activity called TaskOneActivity. When taskOneBtn is pressed, display this activity (3pts). Display Hello Task One in the new Activity centered vertically and horizontally. (2pts)
// TODO - 2. Add the appropriate code to display the recyclerview (5pts)
// TODO - 3. Update the TextView with id=TheTextView with the text "COSC435" when the button is pressed (3pts). Maintain the text when the device is rotated to landscape. (2pts)
// TODO - 4. When the button with id=FragmentButton is pressed, display the SecondFragment by using the FragmentManager (3pts). Restore the original fragment when the back button is pressed.(2pts)
// TODO - 5. Make a network request to fetch the JSON at https://my-json-server.typicode.com/rvalis-towson/final_exam/final, display the "contents" property value in the TextView with id=ApiResultTextView (5pts)
// TODO - 6. A notification will be displayed. When the button is clicked, cancel the notification. (2pts). Make the notification open TaskSixActivity when clicked on (3pts)
// TODO - 7. Change the layout so that the bottom button is on the top. (5pts)
// TODO - 8. There is a resource file called final_exam_data.txt. Read the file and display the contents in id=Task8TextView (2pts). Query the database for Person #5 (3pts)
// TODO - 9. When the button is clicked, a new screen is displayed. Receive the result from the new screen (the edittext value) and log it in MainActivity (5pts)
// TODO - 10. Add a longpress listener to delete a row in the RecyclerView. (5pts) With animation (2pts). With confirmation (3pts) (Note: there is no TaskTenActivity. This task will use TaskTwoActivity)
// TODO - 11. There is a bug that crashes the app. Fix the bug. (5pts)
