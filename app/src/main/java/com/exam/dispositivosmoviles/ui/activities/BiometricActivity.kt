package com.exam.dispositivosmoviles.ui.activities



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityBiometricBinding
import com.exam.dispositivosmoviles.ui.viewmodels.BiometricViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class BiometricActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBiometricBinding

    private val biometricViewModel by viewModels<BiometricViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgHuella.setOnClickListener{
            autenticaBiometrico()
        }
        biometricViewModel.isLoading.observe(this){ isLoading->
            if(isLoading){
                binding.MainLayout.visibility=View.GONE
                binding.CopiaBioLayout.visibility=View.VISIBLE
            }else {
                binding.MainLayout.visibility = View.VISIBLE
                binding.CopiaBioLayout.visibility = View.GONE
            }
        }

        lifecycleScope.launch {
            biometricViewModel.chargingData()
        }


    }

    private fun autenticaBiometrico(){

        if(checkBiometric()){
        val executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion requerida")
            .setSubtitle("Ingrese su huella digital")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
//            .setNegativeButtonText("Cancelar")
            .build()

        val biometricManager = androidx.biometric.BiometricPrompt(
            this,
            executor,
            object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(
                        Intent(
                            this@BiometricActivity,
                            PedidosActivity::class.java))
                }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                    }
            }
        )
        biometricManager.authenticate(biometricPrompt)
        }else{
            Snackbar.make(binding.btnAuth, "No existen los requisitos necesarios", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun checkBiometric() : Boolean{
        var returnValid: Boolean = false
        val biometricManager = BiometricManager.from(this)
        when(biometricManager.canAuthenticate(
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        )){
            BiometricManager.BIOMETRIC_SUCCESS -> {
                returnValid= true
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                returnValid= false
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                returnValid= false
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                val intentPrompt = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                intentPrompt.putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
                startActivity(intentPrompt)
                returnValid= true
            }
        }
        return returnValid
    }

}