package com.exam.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.from
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityNotificationBinding


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
    }




    val CHANNEL : String = "Notificaciones"


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Variedades"
            val descriptionText = "Varias notificaciones simples variadas"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
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
        noti.setSmallIcon(R.drawable.baseline_settings_24)
        noti.setPriority(NotificationCompat.PRIORITY_DEFAULT)
        noti.setStyle(NotificationCompat.BigTextStyle().bigText("Esta es  TEXTO ES LARGO"))

        with(from(this)){
            notify(1,noti.build())
        }
    }




}