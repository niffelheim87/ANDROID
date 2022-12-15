package com.ompava.eltiempo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ompava.eltiempo.model.TiempoModel
import com.ompava.eltiempo.model.TiempoProvider

class TiempoViewModel: ViewModel() {

    val tiempoModel = MutableLiveData<TiempoModel>()

    fun randomTiempo(){
        val currentTiempo: TiempoModel = TiempoProvider.random()
        tiempoModel.postValue(currentTiempo)
    }
}