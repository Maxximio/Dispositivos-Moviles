package com.exam.dispositivosmoviles.ui.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.exam.dispositivosmoviles.databinding.ActivityPedidosBinding
import com.exam.dispositivosmoviles.login.validator.LoginValidator
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name="settings"
)

class PedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPedidosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }
    private fun initClass(){

        binding.btnLogin.setOnClickListener{
            val loginVal = LoginValidator()
            val check = loginVal.checkLogin( binding.editName.text.toString(), binding.editPass.text.toString())

            if (check){
                lifecycleScope.launch(Dispatchers.IO){
                    saveDataStore(binding.editName.text.toString())
                }

                var intent = Intent(
                    this,
                    MainActivity2::class.java
                )

                intent.putExtra("var1" ,
                    ""
                )
                intent.putExtra("var2", 2)
                startActivity(intent)

            }else {
                Snackbar.make(
                    binding.textView2,
                    "Usuario o contraseña invalidos",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            binding.textReg.setOnClickListener {
                Snackbar.make(binding.textView2, "No te puedes registrar por el momento", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private suspend fun saveDataStore(stringData: String){
        dataStore.edit{prefs ->
            prefs[stringPreferencesKey("usuario")] = stringData
            prefs[stringPreferencesKey("session")] = UUID.randomUUID().toString()
            prefs[stringPreferencesKey("email")] = "dispositivosMoviles@uce.edu.ec"
        }
    }
}