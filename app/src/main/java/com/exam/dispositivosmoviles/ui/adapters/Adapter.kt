package com.exam.dispositivosmoviles.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.data.entities.MarvelChars
import com.exam.dispositivosmoviles.databinding.MarvelCharactersBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class Adapter(private val items:List<MarvelChars>):RecyclerView.Adapter<Adapter.MarvelViewHolder>() {
    class MarvelViewHolder(view:View): RecyclerView.ViewHolder(view) {

        private val binding: MarvelCharactersBinding= MarvelCharactersBinding.bind(view)

//        este es la parte que importa
        fun render(item: MarvelChars){
            binding.marvelT.text=item.nombre
            binding.nameT.text=item.comic
    binding.imageView.setOnClickListener{
        Snackbar.make(binding.imageView, item.nombre, Snackbar.LENGTH_SHORT).show()
    }

//            binding.imageView.
    Picasso.get().load(item.imagen).into(binding.imageView);
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Adapter.MarvelViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        return MarvelViewHolder(inflater.inflate(R.layout.marvel_characters,parent,false))
    }

    override fun getItemCount(): Int =items.size


    override fun onBindViewHolder(holder: MarvelViewHolder, position: Int) {

        holder.render(items[position])

    }

}