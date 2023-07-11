package com.exam.dispositivosmoviles.ui.utilities

import android.app.Application
import androidx.room.Room
import com.exam.dispositivosmoviles.data.connections.MarvelConnectionDB
import com.exam.dispositivosmoviles.data.entities.marvel.database.MarvelCharsDB

class DispositivosMoviles : Application() {


// se ejecuta, despues de que se inicia
    override fun onCreate(){
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            MarvelConnectionDB::class.java,
            "marveldb").build()
    }

//    se inicia la app y se crea
    companion object {
//        val name_companion  :  String = "Admin"
        private var db : MarvelConnectionDB? = null

        fun getDbInstance(): MarvelConnectionDB {
            return db!!
        }
    }

}