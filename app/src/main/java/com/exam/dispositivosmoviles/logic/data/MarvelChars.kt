package com.exam.dispositivosmoviles.logic.data

import android.os.Parcelable
import com.exam.dispositivosmoviles.data.entities.marvel.database.MarvelCharsDB
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelChars ( var id: Int,
                         var nombre: String,
                         var comic: String,
                         var imagen: String
                         ):Parcelable

fun MarvelChars.getMarvelCharsDB(): MarvelCharsDB{
    return MarvelChars(
        id,
        nombre,
        comic,
        imagen
    )
}