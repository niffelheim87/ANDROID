package com.ompava.test1ev.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type
import com.ompava.test1ev.R.*


data class Question(

    val question: String,
    val answers: List<String>,
    val image: String,
    val correct_answer: Int


) {

    companion object{
            val questionList:MutableList<Question> = mutableListOf()

        fun loadquestion(context: Context){
            val raw = context.resources.openRawResource(raw.test)
            val rd = BufferedReader(InputStreamReader(raw))

            val listType: Type = object : TypeToken<MutableList<Question>?>() {}.type

            val gson = Gson()
            questionList.clear()

            val preguntas:List<Question> = gson.fromJson(rd, listType)

            questionList.addAll(preguntas)
        }


    }
}

