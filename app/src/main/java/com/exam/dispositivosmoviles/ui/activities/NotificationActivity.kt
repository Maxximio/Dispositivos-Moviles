package com.exam.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat.from
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityNotificationBinding
import com.exam.dispositivosmoviles.ui.utilities.BroadcasterNotifications
import java.util.Calendar


/**
 * Para hacer que valga en el emulador, activar las
 * notificaciones dentro de la aplicacion
 */
class NotificationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotif.setOnClickListener{
            createNotificationChannel()
            sendNotificaction()
        }

        binding.btnNotifProgram.setOnClickListener{

            val calendar=Calendar.getInstance()
            val hora =binding.timePicker.hour
            val minutes= binding.timePicker.minute

            Toast.makeText(this,"La notificacion sera a las: $hora:$minutes ",Toast.LENGTH_SHORT).show()

            calendar.set(Calendar.HOUR,hora)
            calendar.set(Calendar.MINUTE,minutes)
            calendar.set(Calendar.SECOND,0)

            sendNotificationTimePicker(calendar.timeInMillis)
        }
    }

    private fun sendNotificationTimePicker(time:Long){
        val myIntent = Intent(applicationContext, BroadcasterNotifications::class.java)
        val myPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            0,
            myIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager= getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,time,myPendingIntent)
    }

    val CHANNEL : String = "Notificaciones"
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Variedades"
            val descriptionText = "Varias notificaciones simples variadas"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun sendNotificaction(){
        val noti = NotificationCompat.Builder(this, CHANNEL )
        noti.setContentTitle("Primera notificacion")
        noti.setContentText("Prueba")
        noti.setSmallIcon(R.drawable.ic_stat_name)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        noti.setStyle(NotificationCompat.BigTextStyle().bigText("Esta es una notificacion de texto largo :'D"))
        with(from(this)){
            notify(1,noti.build())
        }
    }




}