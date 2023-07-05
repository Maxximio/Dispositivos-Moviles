package com.exam.dispositivosmoviles.data.entities.marvel.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class MarvelCharsDB (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
                         var nombre: String,
                         var comic: String,
                         var imagen: String
): Parcelable