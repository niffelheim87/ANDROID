package com.ompava.eltiempo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ompava.eltiempo.databinding.FragmentTiempoBinding
import com.ompava.eltiempo.viewmodel.TiempoViewModel

class TiempoFragment : Fragment() {

    private lateinit var binding: FragmentTiempoBinding

    private val tiempoViewModel : TiempoViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentTiempoBinding
            .inflate(inflater, container, false)
            .also { binding = it }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tiempoViewModel.tiempoModel.observe(viewLifecycleOwner, Observer {
            binding.tvTiempo.text = it.tiempo
        })

        binding.tvTiempo.setOnClickListener {
            tiempoViewModel.getTiempo()
            }


    }
}