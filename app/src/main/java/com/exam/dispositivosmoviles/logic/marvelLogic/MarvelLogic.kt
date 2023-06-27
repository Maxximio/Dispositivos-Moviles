package com.exam.dispositivosmoviles.logic.marvelLogic

import com.exam.dispositivosmoviles.data.connections.ConnectionApi
import com.exam.dispositivosmoviles.data.endpoints.MarvelEndpoint
import com.exam.dispositivosmoviles.data.entities.MarvelChars
import com.exam.dispositivosmoviles.data.entities.marvel.Marvel

class MarvelLogic {

    suspend fun getCharactersStartsWith(name: String, limit: Int): List<MarvelChars> {
        var itemList = arrayListOf<MarvelChars>()
        var response = ConnectionApi.getService(
            ConnectionApi.typeApi.Marvel,
            MarvelEndpoint::class.java
        ).getCharactersStartsWith(name, limit)


        if (response.isSuccessful) {
            response.body()!!.data.results.forEach() {

                var comics: String = "No available"
                if (it.comics.items.size > 0) {
                    comics = it.comics.items[0].name
                }
                val m = MarvelChars(
                    it.id,
                    it.name,
                    comics,
                    it.thumbnail.path + "." + it.thumbnail.extension
                )
                itemList.add(m)
            }
        }
        return itemList
    }

}