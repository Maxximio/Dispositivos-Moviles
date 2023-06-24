package com.exam.dispositivosmoviles.data.endpoints

import com.exam.dispositivosmoviles.data.entities.jikan.JikanAnimeEntity
import retrofit2.Response
import retrofit2.http.GET

interface JikanEndpoint {

    @GET("top/anime")
   suspend fun getAllAnime() :Response<JikanAnimeEntity>


}