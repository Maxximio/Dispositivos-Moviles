package com.exam.dispositivosmoviles.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.data.entities.MarvelChars

import com.exam.dispositivosmoviles.databinding.FragmentFirstBinding
import com.exam.dispositivosmoviles.logic.lists.ListItems
import com.exam.dispositivosmoviles.ui.activities.DetailsMarvelActivity
import com.exam.dispositivosmoviles.ui.activities.MainActivity
import com.exam.dispositivosmoviles.ui.activities.PedidosActivity
import com.exam.dispositivosmoviles.ui.adapters.MarvelAdapter

class FirstFragment : Fragment() {

    private  lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val list = arrayListOf<String>("Dale Zelda Dale",
            "Pasame la ",
            "Trifuerza que tengo hambre",
            "pasame una botella de Sora grande")

        val adapter = ArrayAdapter<String>(requireActivity(),
            R.layout.simple_layout, list)

        binding.spinner.adapter = adapter
//        binding.listview.adapter= adapter

        /*val rvAdapter = MarvelAdapter(
            ListItems<Any>().returnMarvelChars()
        ) { sendMarvelItem(it) }
        val rvMarvel = binding.rvMarvelChars

        with(rvMarvel){
            this.adapter=rvAdapter
            this.layoutManager=LinearLayoutManager(
                requireActivity(),LinearLayoutManager.VERTICAL,false
            )
        }*/
        binding.spinner.adapter=adapter

        chargeDataRV()

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing=false
        }
        /*rvMarvel.adapter = rvAdapter
        rvMarvel.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)*/
    }

    fun sendMarvelItem(item: MarvelChars){
        val i=Intent(requireActivity(),DetailsMarvelActivity::class.java)
        i.putExtra("name",item)
        startActivity(i)
    }

    fun chargeDataRV(){
        val rvAdapter = MarvelAdapter(
            ListItems<Any>().returnMarvelChars()
        ) { sendMarvelItem(it) }
        val rvMarvel = binding.rvMarvelChars

        with(rvMarvel){
            this.adapter=rvAdapter
            this.layoutManager=LinearLayoutManager(
                requireActivity(),LinearLayoutManager.VERTICAL,false
            )
        }
    }

}