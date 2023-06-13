package com.exam.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityMain2Binding
import com.exam.dispositivosmoviles.ui.fragments.FirstFragment
import com.exam.dispositivosmoviles.ui.fragments.SecondFragment
import com.exam.dispositivosmoviles.ui.fragments.ThirdFragment
import com.exam.dispositivosmoviles.ui.utilities.FragmentManager
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
//                    val fragment1 = FirstFragment()
//                    val transaccion = supportFragmentManager.beginTransaction()
//                    transaccion.add(binding.frmContainer.id, fragment1)
//                    transaccion.addToBackStack(null)
//                    transaccion.commit()
//                    true
                    FragmentManager().replaceFragment(supportFragmentManager,binding.frmContainer.id,FirstFragment())
                    true
                }
                R.id.item_2 -> {
                    // Respond to navigation item 2 reselection
//                    Snackbar.make(binding.respuesta,"Entramos a ayuda",Snackbar.LENGTH_LONG).show()
//                    true
//                    val fragment2 = SecondFragment()
//                    val transaccion = supportFragmentManager.beginTransaction()
//                    transaccion.add(binding.frmContainer.id, fragment2)
//                    transaccion.addToBackStack(null)
//                    transaccion.commit()

                    FragmentManager().replaceFragment(supportFragmentManager,binding.frmContainer.id,SecondFragment())
                    true

                }
                R.id.item_3 -> {
                    // Respond to navigation item 2 reselection
//                    val fragment3 = ThirdFragment()
//                    val transaccion = supportFragmentManager.beginTransaction()
//                    transaccion.add(binding.frmContainer.id, fragment3)
//                    transaccion.addToBackStack(null)
//                    transaccion.commit()
                    FragmentManager().replaceFragment(supportFragmentManager,binding.frmContainer.id,ThirdFragment())
                    true


                    true
                }
                else -> false
            }
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}