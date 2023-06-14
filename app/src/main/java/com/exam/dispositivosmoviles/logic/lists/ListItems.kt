package com.exam.dispositivosmoviles.logic.lists

import com.exam.dispositivosmoviles.data.entities.LoginUser
import com.exam.dispositivosmoviles.data.entities.MarvelChars

class ListItems<T> {

    fun returnItems(): List<LoginUser>{
        var items=listOf<LoginUser>(
            LoginUser("1","1"),
            LoginUser("2","1"),
            LoginUser("3","1"),
            LoginUser("4","1"),
            LoginUser("5","1")
        )
        return items
    }

    fun returnMarvelChars(): List<MarvelChars>{
        val items=listOf(
            MarvelChars(
                1,"Punisher","The Punisher (1987)",
                "https://comicvine.gamespot.com/a/uploads/scale_small/11125/111253019/6465266-punisher_vol_12_1_textless.jpg"
            ),
            MarvelChars(
                2,"Deadpool","Deadpool (1997)",
                "https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/8926324-large-2680196.jpg"
            ),
            MarvelChars(
                3,"Daredevil", "Daredevil (1964)","https://comicvine.gamespot.com/a/uploads/scale_small/11118/111187046/7397359-0398898002-EQH1ysWWsAA7QLf"
            ),
            MarvelChars(
                4,"Blade","Avengers (1980)","https://comicvine.gamespot.com/a/uploads/scale_small/12/124259/7949396-heroes_reborn_vol_2_1_devil_dog_comics_exclusive_virgin_variant.jpg"
            ),
            MarvelChars(
                5,"Invisible Woman","Fantastic Four(1961)","https://comicvine.gamespot.com/a/uploads/scale_small/11141/111413247/7267710-4df69cb3-7b54-480e-89e5-2c7e68c52b1b_rw_1200.jpg"
            )

        )
        return items
    }

}