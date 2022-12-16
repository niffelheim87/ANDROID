package com.ompava.eltiempo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ompava.eltiempo.R
import com.ompava.eltiempo.model.TiempoProvider


class TiempoViewModel(application: Application) : AndroidViewModel(application) {
    var tiempoProvider = TiempoProvider.tiempoLiveData

    var imageLiveData: LiveData<Int>
    var tituloLiveData: LiveData<String>



    init {

        imageLiveData = Transformations.switchMap(tiempoProvider) { image ->
                var imageID: Int = when (image) {
                    "clouds" -> R.drawable.clouds
                    "rain" -> R.drawable.rain
                    "sun" -> R.drawable.sun
                    "wind" -> R.drawable.wind
                    else -> R.drawable.sun

                }
                return@switchMap MutableLiveData<Int>(imageID)


            return@switchMap null
        }

        tituloLiveData = Transformations.switchMap(tiempoProvider) { image ->
            var titulo: String? = tiempoProvider.value



            return@switchMap MutableLiveData<String>(titulo)


            return@switchMap null
        }


    }


}