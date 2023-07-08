package com.exam.dispositivosmoviles.data.dao.marvel

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.exam.dispositivosmoviles.data.entities.marvel.database.MarvelCharsDB
import kotlinx.coroutines.selects.select

@Dao
interface MarvelCharsDAO {

    @Query("select * from MarvelCharsDB")
    fun getAllCharacters() : List<MarvelCharsDB>

    @Query("select * from MarvelCharsDB where id=:pk")
    fun getOneCharacter(pk: Int): MarvelCharsDB

    @Insert
    fun insertMarvelChar(ch: List<MarvelCharsDB>)

//    @Delete
//    fun eliminarMarvelChar(pk: Int):MarvelCharsDB

}