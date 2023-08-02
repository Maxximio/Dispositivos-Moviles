package com.exam.dispositivosmoviles.ui.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.exam.dispositivosmoviles.logic.data.MarvelChars
import com.exam.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgressViewModel : ViewModel(){

    val progressState = MutableLiveData<Int>()
    val items = MutableLiveData<List<MarvelChars>>()

    fun processBackground(tiempo: Long){
        viewModelScope.launch (Dispatchers.IO){
            val state = View.VISIBLE
            progressState.postValue(state)
            delay(tiempo)
            val state1 = View.GONE
            progressState.postValue(state1)
        }

    }

    fun sumprocessBackground1(tiempo: Long){
        viewModelScope.launch (Dispatchers.IO){

            val state = View.VISIBLE
            progressState.postValue(state)
            var total = 0
            for(i in 1 ..1000*tiempo.toInt()){
                total += i
            }
//            delay(tiempo)
            val state1 = View.GONE
            progressState.postValue(state1)
        }
    }

   suspend fun getMarvelChars(offset: Int, limit: Int){
       progressState.postValue(View.VISIBLE)

       val newItems =  MarvelLogic()
           .getAllMarvelChars(offset, limit)
        items.postValue(newItems)
    }
}