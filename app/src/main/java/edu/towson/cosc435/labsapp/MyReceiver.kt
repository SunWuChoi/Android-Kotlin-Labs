package edu.towson.cosc435.labsapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import edu.towson.cosc435.labsapp.interfaces.ISongController

class MyReceiver(val controller : ISongController) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            IMAGE_ACTION -> {
                controller.launchNewSongScreen()
            }
        }
    }

    companion object{
        val IMAGE_ACTION = "IMAGES_AVAILABLE"
    }
}
