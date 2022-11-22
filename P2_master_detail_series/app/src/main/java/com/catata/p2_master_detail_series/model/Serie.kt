package com.catata.p2_master_detail_series.model

import android.content.Context
import com.catata.p2_master_detail_series.R
import com.catata.p2_master_detail_series.R.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

data class Serie(
    val id:Int,
    val name: String,
    val language:String,
    val genres: List<String>,
    val status:String,
    val premiered:String,
    val officialSite:String,
    val rating:Float,
    val image:String,
    val summary:String
) {

    companion object{
        val serieList:MutableList<Serie> = mutableListOf()

        fun loadSeries(context:Context){
            val raw = context.resources.openRawResource(raw.datos_json)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<Serie>?>() {}.type

            val gson = Gson()
            serieList.clear()

            val series:List<Serie> = gson.fromJson(rd, listType)

            serieList.addAll(series)
        }

        fun getSerieById(id:Int): Serie?{
            val series = serieList.filter {
                it.id == id
            }


            return if(series.isNotEmpty()) series[0] else null
        }
    }
}