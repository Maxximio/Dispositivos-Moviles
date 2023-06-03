package com.exam.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.TextView
import com.exam.dispositivosmoviles.databinding.ActivityMainBinding
import com.exam.dispositivosmoviles.login.validator.LoginValidator
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {





    private lateinit var binding: ActivityMainBinding
    var a = 2

    @SuppressLint("MissingInflateId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("UCE", "Entrando a Create")
    }

    override fun onStart() {
        super.onStart()
        initClass()

    }

//    private fun initClass() {
//        var boton1 = binding.button
//        var txtBuscar = binding.textView3
//        boton1.elevation
//
////        sumar
//
//
//
//        binding.button.setOnClickListener{
//            boton1.text="Hola"
//            txtBuscar.text="Buscando..."
//            binding.textView3.text = "El Codigo ejecutado"
//            var f=Snackbar.make(
//                binding.button, "Es mensaje", Snackbar.LENGTH_LONG
//            )
//            f.show()
//        }
//
//
//
//
//
//
//    }

    private fun initClass(){



        binding.button.setOnClickListener{
            val loginVal = LoginValidator()
            val check = loginVal.checkLogin( binding.editName.text.toString(), binding.editPass.text.toString())




//            if ( binding.editName.text.toString() == "admin" && binding.editName.text.toString() == "admin"){
            if (check){
                var intent = Intent(
                    this,
                    MainActivity2::class.java
                )



                intent.putExtra("var1" ,
                    ""
//                binding.edit_.text.toString()
                )
                intent.putExtra("var2", 2)
                startActivity(intent)
            }else{
                Snackbar.make(binding.textView, "Usuario o contraseña invalidos", Snackbar.LENGTH_LONG).show()
            }



        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }


}