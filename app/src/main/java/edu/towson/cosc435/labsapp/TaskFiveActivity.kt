package edu.towson.cosc435.labsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_task_five.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import org.xml.sax.Parser
import java.lang.Exception
import java.net.URL

class TaskFiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_five)

        lateinit var apiResponse: String
//
//        lifecycleScope.launch {
//            try {
//                withContext(Dispatchers.IO){
//                    apiResponse = URL("https://my-json-server.typicode.com/rvalis-towson/final_exam/final").readText()
//                }
//
//            } catch (e: Exception){
//
//            }
//        }
//




        ApiResultTextView.text = parse(apiResponse)?.getString("message")

    }

    fun parse(json: String): JSONObject? {
        var jsonObject: JSONObject? = null
        try {
            jsonObject = JSONObject(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return jsonObject
    }
}
