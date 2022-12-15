package com.ompava.eltiempo.model

class TiempoProvider {
    companion object{

        fun random():TiempoModel{
            val position: Int = (0..3).random()
            return tiempo[position]
        }

        private val tiempo = listOf<TiempoModel>(
            TiempoModel(tiempo = "sun"),
            TiempoModel(tiempo = "clouds"),
            TiempoModel(tiempo = "rain"),
            TiempoModel(tiempo = "wind")
        )
    }

}