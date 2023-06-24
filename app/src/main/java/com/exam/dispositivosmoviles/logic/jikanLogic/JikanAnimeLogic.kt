package com.exam.dispositivosmoviles.logic.jikanLogic

import com.exam.dispositivosmoviles.data.connections.ConnectionApi
import com.exam.dispositivosmoviles.data.endpoints.JikanEndpoint
import com.exam.dispositivosmoviles.data.entities.MarvelChars

class JikanAnimeLogic {

   suspend fun getAllAnimes() : List<MarvelChars>{
        var call = ConnectionApi.getJikanConnection()
        val response = call.create(JikanEndpoint::class.java).getAllAnime()
        var itemList = arrayListOf<MarvelChars>()

        if (response.isSuccessful){
            response.body()!!.data.forEach{
                val m = MarvelChars(
                    it.mal_id,
                    it.title,
                    it.titles[0].title,
                    it.images.jpg.image_url
                )
                itemList.add(m)

            }
        }
        return itemList
    }
}