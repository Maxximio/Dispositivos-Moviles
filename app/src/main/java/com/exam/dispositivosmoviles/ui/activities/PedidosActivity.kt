package com.exam.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Instrumentation.ActivityResult
import android.app.SearchManager
import android.content.ContentProviderClient
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.speech.RecognizerIntent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker.PermissionResult
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope

import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityPedidosBinding
import com.exam.dispositivosmoviles.login.validator.LoginValidator
import com.exam.dispositivosmoviles.ui.utilities.MyLocationManager
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.Permission
import java.util.Locale
import java.util.UUID

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    name = "settings"
)

class PedidosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPedidosBinding
    //Ubicacion y GPS
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var client: SettingsClient
    private lateinit var locationSettingsRequest: LocationSettingsRequest

//    private val client= LocationServices.getSettingsClient(this)//el this da error por problemas en el ciclo de vida
//    private val locationSettingsRequest =LocationSettingsRequest.Builder()
//        .addLocationRequest(locationRequest).build()

    private  var currentLocation: Location? = null

    private val speechToText =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val sn = Snackbar.make(
                binding.textView3,
                "",
                Snackbar.LENGTH_LONG
            )
            var message = ""
            when (activityResult.resultCode) {
                RESULT_OK -> {
                    val msg =
                        activityResult.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                            ?.get(0).toString()
                    if (msg.isNotEmpty()) {
                        val intent = Intent(
                            Intent.ACTION_WEB_SEARCH
                        )
                        intent.setClassName(
                            "com.google.android.googlequicksearchbox",
                            "com.google.android.googlequicksearchbox.SearchActivity"
                        )
                        intent.putExtra(SearchManager.QUERY, msg)
                        startActivity(intent)
                    }
                }

                RESULT_CANCELED -> {
                    sn.setBackgroundTint(resources.getColor((R.color.black)))
                    message = "Proceso cancelado"
                }

                else -> {
                    sn.setBackgroundTint(resources.getColor((R.color.black)))
                    message = "Ocurrio un error"
                }
            }
            sn.setText(message)
            sn.show()
        }

    @SuppressLint("MissingPermission")
    private val locationContract =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                isGranted ->
            when (isGranted) {
                true -> {
                    client.checkLocationSettings(locationSettingsRequest).apply {
                        addOnSuccessListener {
                            val task = fusedLocationProviderClient.lastLocation
                            task.addOnSuccessListener { location ->
                                fusedLocationProviderClient.requestLocationUpdates(
                                    locationRequest,
                                    locationCallback,
                                    Looper.getMainLooper()
                                )
                            }
                        }

                        addOnFailureListener{ex->
                            if(ex is ResolvableApiException){
                                ex.startResolutionForResult(
                                    this@PedidosActivity,
                                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                                )
                            }
                        }
                    }
                }

                shouldShowRequestPermissionRationale(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                }
                false -> {
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(LocationResult: LocationResult) {
                super.onLocationResult(LocationResult)
                if (LocationResult != null) {
                    LocationResult.locations.forEach { location ->
                        currentLocation = location
                        Log.d(
                            "UCE",
                            "Ubicacion: ${location.latitude}, " + "${location.longitude}"
                        )
                    }
                }
            }
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)



        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 2000
        )
            .setMaxUpdates(3)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(LocationResult: LocationResult) {
                super.onLocationResult(LocationResult)
                if (LocationResult != null) {
                    LocationResult.locations.forEach { location ->
                        currentLocation = location
                        Log.d(
                            "UCE",
                            "Ubicacion: ${location.latitude}, " + "${location.longitude}"
                        )
                    }
                }
            }
        }

        client=LocationServices.getSettingsClient(this)
        locationSettingsRequest=LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest).build()

    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
    private suspend fun saveDataStore(stringData: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("usuario")] = stringData
            prefs[stringPreferencesKey("session")] = UUID.randomUUID().toString()
            prefs[stringPreferencesKey("email")] = "dispositivosMoviles@uce.edu.ec"
        }
    }

    @SuppressLint("MissingPermission")
    private fun initClass() {
        binding.btnLogin.setOnClickListener {
            val loginVal = LoginValidator()
            val check = loginVal.checkLogin(
                binding.editName.text.toString(),
                binding.editPass.text.toString()
            )
            if (check) {
                lifecycleScope.launch(Dispatchers.IO) {
                    saveDataStore(binding.editName.text.toString())
                }
                var intent = Intent(
                    this,
                    MainActivity2::class.java
                )
                intent.putExtra(
                    "var1",
                    ""
                )
                intent.putExtra("var2", 2)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.textView2,
                    "Usuario o contraseña invalidos",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
            binding.textReg.setOnClickListener {
                Snackbar.make(
                    binding.textView2,
                    "No te puedes registrar por el momento",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            binding.btnTwitter.setOnClickListener {
//                Usando los permisos
                Log.d("UCE", "LLego")
                locationContract.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
            }

            val appResultLocal =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        resultActivity ->
                    val sn = Snackbar.make(
                        binding.textView3,
                        "",
                        Snackbar.LENGTH_LONG
                    )

                    var color: Int = R.color.black
                    var message = when (resultActivity.resultCode) {
                        RESULT_OK -> {
                            sn.setBackgroundTint(resources.getColor((R.color.blue_dm)))
                            resultActivity.data?.getStringExtra("result").orEmpty()
                        }
                        RESULT_CANCELED -> {
                            sn.setBackgroundTint(resources.getColor((R.color.black)))
                            resultActivity.data?.getStringExtra("result").orEmpty()
                        }
                        else -> {
                            resultActivity.data?.getStringExtra("result").orEmpty()
                        }
                    }
                    sn.setText(message)
                    sn.show()
                }

            binding.btnFace.setOnClickListener() {



                val intentSpeech = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
                intentSpeech.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intentSpeech.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE,
                    Locale.getDefault()
                )
                intentSpeech.putExtra(
                    RecognizerIntent.EXTRA_PROMPT, "di algo"
                )
                speechToText.launch(intentSpeech)
            }
            binding.btnFace.setOnClickListener {
                val resIntent = Intent(this, ResultActivity::class.java)
                appResultLocal.launch(resIntent)
            }
    }

    private fun test(){
        var location=MyLocationManager(this)
        location.getUserLocation()
    }

}