package com.ompava.eltiempo.model


import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

typealias  OnTiempo = (tiempo: String) -> Unit

class TiempoProvider {


    companion object {

        fun random(): TiempoModel {
            val position = (0..3).random()
            return tiempoList[position]
        }

        val tiempoList = listOf<TiempoModel>(
            TiempoModel(tiempo = "sun"),
            TiempoModel(tiempo = "clouds"),
            TiempoModel(tiempo = "rain"),
            TiempoModel(tiempo = "wind")
        )

        var tiempoJob: Job? = null

        fun startTiempo(onTiempo: OnTiempo) {
            if (tiempoJob == null || tiempoJob!!.isCancelled || tiempoJob!!.isCompleted) {
                tiempoJob = CoroutineScope(Dispatchers.IO).launch {

                    while (true) {
                        onTiempo(random().tiempo)
                        delay(1000)
                    }

                }
            }
        }

        fun stopTiempo() {
            tiempoJob?.let {
                if (it.isActive)
                    it.cancel()
            }
        }

        val tiempoLiveData: LiveData<String> = object:LiveData<String>(){
            override fun onActive() {
                super.onActive()
                startTiempo {
                        tiempo -> postValue(tiempo)
                }
            }

            override fun onInactive() {
                super.onInactive()
                stopTiempo()
            }
        }
    }
}