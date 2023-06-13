package com.exam.dispositivosmoviles.ui.utilities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

import com.exam.dispositivosmoviles.ui.fragments.ThirdFragment

class FragmentManager {
    fun replaceFragment(manager: FragmentManager, container: Int, fragment: Fragment){

        with(manager.beginTransaction()){
            replace(container,fragment)
            addToBackStack(null)
            commit()
        }

        fun addFragment(manager: FragmentManager, container: Int, fragment: Fragment) {

            with(manager.beginTransaction()) {
                add(container, fragment)
                commit()
            }
        }
//        val transaccion = manager.beginTransaction()
//        transaccion.add(container, fragment)
//        transaccion.addToBackStack(null)
//        transaccion.commit()
    }
}