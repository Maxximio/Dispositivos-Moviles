package com.exam.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityProgressBinding
import com.exam.dispositivosmoviles.logic.data.MarvelChars
import com.exam.dispositivosmoviles.ui.viewmodels.ProgressViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProgressActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProgressBinding

    private val progressViewModel by viewModels<ProgressViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressViewModel.progressState.observe(this, {
            binding.progressBar1.visibility = it })

        progressViewModel.items.observe(this, {
            Toast.makeText(this, it[0].nombre, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, NotificationActivity::class.java))
        })

        binding.btnProcess1.setOnClickListener{
            progressViewModel.processBackground(3000)
        }

        binding.btnProcess2.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO) {
                progressViewModel.getMarvelChars(0,90)
            }


        }
    }


}