package com.exam.dispositivosmoviles.logic.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelChars ( var id: Int,
                         var nombre: String,
                         var comic: String,
                         var imagen: String
                         ):Parcelable