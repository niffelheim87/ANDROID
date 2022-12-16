package com.ompava.eltiempo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ompava.eltiempo.databinding.ActivityMainBinding
import com.ompava.eltiempo.viewmodel.TiempoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tiempoViewModel : TiempoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tiempoViewModel = ViewModelProvider(this)[TiempoViewModel::class.java]

        tiempoViewModel.imageLiveData.observe(this){ imageID ->
            binding.ivTiempo.setImageResource(imageID)


        }

        tiempoViewModel.tituloLiveData.observe(this){ titulo ->
            binding.tvTitulo.setText(titulo)


        }

    }
}