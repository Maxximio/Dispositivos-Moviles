package com.exam.dispositivosmoviles.logic.marvelLogic

import com.exam.dispositivosmoviles.data.connections.ConnectionApi
import com.exam.dispositivosmoviles.data.endpoints.MarvelEndpoint
import com.exam.dispositivosmoviles.data.entities.marvel.getMarvelChars
import com.exam.dispositivosmoviles.logic.data.MarvelChars

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

    suspend fun getAllMarvelChars(offset: Int, limit: Int): List<MarvelChars> {
        var itemList = arrayListOf<MarvelChars>()

        var response = ConnectionApi.getService(
            ConnectionApi.typeApi.Marvel,
            MarvelEndpoint::class.java
        ).getAllMarvelChars(offset, limit)

        if (response!=null) {
            response.body()!!.data.results.forEach() {

                val m=it.getMarvelChars()
                itemList.add(m)

            }
        }
        return itemList
    }

}