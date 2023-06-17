package com.exam.dispositivosmoviles.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.exam.dispositivosmoviles.R

import com.exam.dispositivosmoviles.databinding.FragmentFirstBinding
import com.exam.dispositivosmoviles.logic.lists.ListItems
import com.exam.dispositivosmoviles.ui.adapters.Adapter

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

        val rvAdapter = Adapter(ListItems<Any>().returnMarvelChars())
        val rvMarvel = binding.rvMarvelChars
        rvMarvel.adapter = rvAdapter
        rvMarvel.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)
    }

}