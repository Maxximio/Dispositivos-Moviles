package com.exam.dispositivosmoviles.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

}