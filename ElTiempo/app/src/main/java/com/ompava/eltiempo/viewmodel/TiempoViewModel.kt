package com.ompava.eltiempo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ompava.eltiempo.model.TiempoModel
import com.ompava.eltiempo.model.TiempoProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TiempoViewModel: ViewModel() {

    val tiempoModel = MutableLiveData<TiempoModel>()

    fun getTiempo(){

            val currentTiempo: TiempoModel = TiempoProvider.random()
        tiempoModel.value = currentTiempo

    }


}