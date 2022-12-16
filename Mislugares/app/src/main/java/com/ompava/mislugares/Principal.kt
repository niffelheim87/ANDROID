package com.ompava.mislugares


object Principal {
    @JvmStatic
    fun main(main: Array<String>) {
        val lugares = LugaresLista()
        lugares.añadeEjemplos()
        for (i in 0..lugares.tamaño()-1) {
            println(lugares.elemento(i).toString())
        }

    }


}