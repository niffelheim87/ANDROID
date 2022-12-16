package com.ompava.eltiempo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ompava.eltiempo.databinding.ActivityMainBinding
import com.ompava.eltiempo.viewmodel.TiempoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tiempoViewModel : TiempoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tiempoViewModel.tiempoModel.observe(this, Observer {
            binding.tvTiempo.text = it.tiempo
        })

        binding.tvTiempo.setOnClickListener {
            tiempoViewModel.getTiempo()
        }
    }
}