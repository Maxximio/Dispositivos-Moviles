package com.exam.dispositivosmoviles.ui.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
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

            binding.btnTwitter.setOnClickListener{
                                                        //tel:0123456789
//                val intent =Intent(Intent.ACTION_VIEW, Uri.parse(
//                    "http://google.com.ec"))//geo:-0.200628,-78.5786066
//
                val intent=Intent(Intent.ACTION_WEB_SEARCH)
                intent.setClassName("com.google.android.googlequicksearchbox"
                    ,"com.google.android.googlequicksearchbox.SearchActivity"
                )
                intent.putExtra(SearchManager.QUERY,"UCE")
                startActivity(intent)
                }

            val appResultLocal= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                resultActivity ->
                when(resultActivity.resultCode) {
                    RESULT_OK -> {

                        Snackbar.make(binding.textView3,
                            "Resultado exitoso",
                            Snackbar.LENGTH_LONG).show()
                       // Log.d("UCE","Resultado exitoso")
                    }
                    RESULT_CANCELED -> {
                        //Log.d("UCE","Resultado fallido")
                        Snackbar.make(binding.textView3,
                            "Resultado fallido",
                            Snackbar.LENGTH_LONG).show()
                    }

                    else -> {
                    //Log.d("UCE","Resultado dudoso")
                        Snackbar.make(binding.textView3,
                            "No tengo idea",
                            Snackbar.LENGTH_LONG).show()
                    }
                }
            }

            binding.btnFace.setOnClickListener{
                val resIntent= Intent(this,ResultActivity::class.java)
                appResultLocal.launch(resIntent)
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