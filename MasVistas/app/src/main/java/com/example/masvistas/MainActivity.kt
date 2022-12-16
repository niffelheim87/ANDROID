package com.example.masvistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var boton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        boton = findViewById<Button>(R.id.button)
        boton.setOnClickListener(){
            sePulsa()
        }
        entrada.setOnClickListener(){
            sePulsa0(entrada)
        }
    }


    fun sePulsa(){
        Toast.makeText(this,"Pulsado", Toast.LENGTH_SHORT).show()
    }
    fun sePulsa0(view: View){
        entrada?.setText(entrada?.getText().toString() + view?.tag as String)
    }
}