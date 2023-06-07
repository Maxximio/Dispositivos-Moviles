package com.exam.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityMain2Binding
import com.google.android.material.snackbar.Snackbar


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

       /* intent.extras.let {
             var name  = it?.getString("var1")
//            var name = it.getString("var1")
            Log.d("UCE", "${name} - INTRODUZCA NOMBRE")
//            var number = it?.getInt("var2")
            binding.respuesta.text = "Bienvenido: " + name.toString()
        }*/

        Log.d("UCE", "Entrando a Start")
        initClass()

    }

    private fun initClass(){
//        binding.checkBox.setOnCheckedChangeListener().equals()

        binding.button2.setOnClickListener{
            var intent = Intent(
                this,
                PedidosActivity::class.java
            )

            startActivity(intent)
        }

        binding.bottomNavigation.setOnItemSelectedListener {item ->
            when(item.itemId) {
                R.id.item_1 -> {
                    var suma: Int=0

                    for (i in listOf(1,2,3)){
                        suma+=1
                    }

                    Snackbar.make(binding.respuesta,"La suma es: ${suma}",Snackbar.LENGTH_LONG).show()
                    // Respond to navigation item 1 reselection
                    true
                }
                R.id.item_2 -> {
                    // Respond to navigation item 2 reselection
                    Snackbar.make(binding.respuesta,"Entramos a ayuda",Snackbar.LENGTH_LONG).show()
                    true

                }
                R.id.item_3 -> {
                    // Respond to navigation item 2 reselection
                    Snackbar.make(binding.respuesta,"Entramos a mapa",Snackbar.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

    }





    override fun onDestroy() {
        super.onDestroy()
    }

}