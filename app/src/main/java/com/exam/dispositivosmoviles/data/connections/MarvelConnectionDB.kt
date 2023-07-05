package com.exam.dispositivosmoviles.data.connections

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exam.dispositivosmoviles.data.dao.marvel.MarvelCharsDAO
import com.exam.dispositivosmoviles.data.entities.marvel.database.MarvelCharsDB

@Database(
    entities =[MarvelCharsDB::class],
    version = 1
    )
abstract class MarvelConnectionDB: RoomDatabase() {

abstract fun mairvelDAO() : MarvelCharsDAO

}