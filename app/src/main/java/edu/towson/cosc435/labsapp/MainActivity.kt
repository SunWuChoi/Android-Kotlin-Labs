package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO - 1. attach a click listener to the convert button and call handleClick
        convert_btn.setOnClickListener{ handleClick() }
    }

    private fun handleClick() {
        try{
            // TODO - 2. Get the text from the edit text (.editableText.ToString())
            val enteredText = input_temp_et.editableText.toString()

            Toast.makeText(this, "Entered: $enteredText", Toast.LENGTH_SHORT).show()
            // TODO - 3. Get the selected convert type from the selected RadioButton
            val convertType = when(radio_group.checkedRadioButtonId){
                R.id.c2f_radio_btn -> ConvertType.C2F
                R.id.f2c_radio_btn -> ConvertType.F2C
                else -> throw Exception()
            }

            val enteredValue = enteredText.toDouble()

            val result = convertTemp(enteredValue, convertType)

            // TODO - 6. Call the convertTemp function to convert the value

            // TODO - 7. Set the result text based on the result (or an error message)
            result_tv.text = result.toString()
        }catch(e: Exception){
            result_tv.text = resources.getString(R.string.error_string)
        }
    }
}
