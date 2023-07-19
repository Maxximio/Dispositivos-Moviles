package com.exam.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.button3.setOnClickListener{
            val i = Intent()
            i.putExtra("result", "Resultado exitoso")
            setResult(RESULT_OK,i)
            finish()
        }

        binding.button4.setOnClickListener{
            val i = Intent()
            i.putExtra("result", "Resultado exitoso")
            setResult(RESULT_CANCELED,i)
            finish()
        }
    }
}