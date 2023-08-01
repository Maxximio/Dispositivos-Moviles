package com.exam.dispositivosmoviles.ui.utilities

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.ui.activities.NotificationActivity

class BroadcasterNotifications: BroadcastReceiver() {

    val CHANNEL : String = "Notificaciones"

    override fun onReceive(context: Context, intent: Intent?) {

        /*val myIntent = Intent(context,NotificationActivity::class.java)
        myIntent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val myPendingIntent = PendingIntent.getActivity()*/

        val noti = NotificationCompat.Builder(context, CHANNEL )
        noti.setContentTitle("Primera notificacion")
        noti.setContentText("Prueba")
        noti.setSmallIcon(R.drawable.ic_stat_name)
        noti.setPriority(NotificationCompat.PRIORITY_HIGH)
        noti.setStyle(NotificationCompat.BigTextStyle().bigText("Alarma"))

        val notificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        notificationManager.notify(1,noti.build())

    }
}