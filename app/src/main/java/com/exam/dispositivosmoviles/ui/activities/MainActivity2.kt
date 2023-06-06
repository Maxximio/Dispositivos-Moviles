package com.exam.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        intent.extras.let {
             var name  = it?.getString("var1")
//            var name = it.getString("var1")
            Log.d("UCE", "${name} - INTRODUZCA NOMBRE")
//            var number = it?.getInt("var2")
            binding.respuesta.text = "Bienvenido: " + name.toString()
        }

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


    }




    override fun onDestroy() {
        super.onDestroy()
    }

}