package com.exam.dispositivosmoviles.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityMain2Binding
import com.exam.dispositivosmoviles.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()

    }

    private fun initClass() {
        binding.checkBox.setOnClickListener{
            onDestroy()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }

}