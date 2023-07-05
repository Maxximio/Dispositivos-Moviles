package com.exam.dispositivosmoviles.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.exam.dispositivosmoviles.logic.data.MarvelChars
import com.exam.dispositivosmoviles.databinding.ActivityDetailsMarvelBinding
import com.squareup.picasso.Picasso

class DetailsMarvelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMarvelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsMarvelBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        //var name: String? =""
        /*intent.extras?.let {
            name=it.getString("name")
        }
        if(!name.isNullOrEmpty()){
            binding.textName.text=name
        }*/
        val item=intent.getParcelableExtra<MarvelChars>("name")

        if(item != null) {
            binding.textName.text=item.nombre
            Picasso.get().load(item.imagen).into(binding.imgMarvel)
        }
    }
}