package com.ompava.mislugares

import com.ompava.mislugares.RepositorioLugares.elemento
import com.ompava.mislugares.RepositorioLugares
import com.ompava.mislugares.LugaresLista

class Principal {
    var lugares: RepositorioLugares = LugaresLista()

    init {
        println(lugares.elemento(i).toString())
    }
}