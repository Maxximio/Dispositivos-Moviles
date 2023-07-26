package com.exam.dispositivosmoviles.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityCameraBinding
import com.exam.dispositivosmoviles.databinding.ActivityResultBinding

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.boton.setOnClickListener{
            val intent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraResult.launch(intent)
        }
        binding.imagen.setOnClickListener{

            val shareIntent =Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT,"Dale Paulo dale")
            shareIntent.setType("text/plain")

            startActivity(Intent.createChooser(shareIntent,"Compartir"))
        }

    }

    private val cameraResult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
       when(result.resultCode){
           Activity.RESULT_OK->{
               val image=result.data?.extras?.get("data") as Bitmap
               binding.imagen.setImageBitmap(image)
           }
           Activity.RESULT_CANCELED->{}
       }
    }

}