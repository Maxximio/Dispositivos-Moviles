package com.exam.dispositivosmoviles.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.databinding.ActivityMainBinding
import com.exam.dispositivosmoviles.databinding.FragmentFirstBinding


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
        binding.listview.adapter= adapter

    }

}