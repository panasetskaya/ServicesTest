package ru.sumin.servicestest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.let {
            val notificationManager =
                getSystemService(it, NotificationManager::class.java) as NotificationManager

            createNotificationChannel(notificationManager)

            val notification  = NotificationCompat.Builder(it,
                CHANNEL_ID
            )
                .setContentTitle("AlarmReceiver notification")
                .setContentText("Text")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build()

            notificationManager.notify(NOTIFICATION_ID,notification)
        }
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AlarmReceiver::class.java)

        }
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
    }
}