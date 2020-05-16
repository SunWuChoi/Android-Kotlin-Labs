package edu.towson.cosc435.labsapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_task_six.*

class TaskSixActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_six)

        createNotificationChannel()
        showNotification(1, 100)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "SERVICE_DEMO_CHANNEL"
            val descriptionText = "Notification channel for ServiceDemo"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIF_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(id: Int, progress: Int) {
        val builder = NotificationCompat.Builder(this, NOTIF_ID)
        builder.setContentTitle("Hello from ExampleService (${id})")
            .setContentText("Click this notification to launch the Activity")
            .setSmallIcon(android.R.drawable.btn_dialog)
            .setVibrate(longArrayOf(1000L))
            .setProgress(10, progress, false)
            .setTicker("Hello World")
            .setAutoCancel(true) // make sure the notification closes on click
        val notification = builder.build()
        NotificationManagerCompat.from(this).notify(id, notification)
    }

    companion object {
        val NOTIF_ID = "FinalExam"
    }
}
