package edu.towson.cosc435.labsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import edu.towson.cosc435.labsapp.models.Song

class MessageQueue {

    companion object {
        val Channel: MutableLiveData<Song> = MutableLiveData()
    }

}