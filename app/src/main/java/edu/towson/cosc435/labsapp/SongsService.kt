package edu.towson.cosc435.labsapp

import android.app.*
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import edu.towson.cosc435.labsapp.database.SongDatabaseRepository
import edu.towson.cosc435.labsapp.models.Song
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class SongsService : JobService(), CoroutineScope{
    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartJob(p0: JobParameters?): Boolean {
        // fetch a new song from the web api...
        Log.d(TAG,"Songs Service job started")
        val song = Song(UUID.randomUUID(), "Song from service", "Service", 1, true, "https://picsum.photos/400/400?image=101")
        val repo = SongDatabaseRepository(this)
        launch {
            repo.addSong(song)
            val notif = createNotification()
            NotificationManagerCompat.from(this@SongsService).notify(NOTIF_ID, notif)
            MessageQueue.Channel.postValue(song)
        }

        return true
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NewSongsChannel"
            val descriptionTxt = "Notification channel for recieving new songs."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionTxt
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    private fun createNotification(): Notification {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("New song is available!")
            .setContentTitle("New Song!")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

    }

    companion object {
        val TAG = SongsService::class.java.simpleName
        val CHANNEL_ID = "NewSongChannelId"
        val NOTIF_ID = 1
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO


}
