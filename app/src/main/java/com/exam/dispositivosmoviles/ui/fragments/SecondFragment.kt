package com.exam.dispositivosmoviles.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exam.dispositivosmoviles.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private  lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}