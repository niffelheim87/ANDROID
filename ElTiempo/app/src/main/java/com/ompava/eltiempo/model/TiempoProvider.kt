package com.ompava.eltiempo.model

class TiempoProvider {
    companion object{

        fun random():TiempoModel{
            val position = (0..3).random()
            return tiempoList[position]
        }

        val tiempoList = listOf<TiempoModel>(
            TiempoModel(tiempo = "sun"),
            TiempoModel(tiempo = "clouds"),
            TiempoModel(tiempo = "rain"),
            TiempoModel(tiempo = "wind")
        )

    }

}