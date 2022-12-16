package com.example.tabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val texto : TextView = findViewById(R.id.texto)
        val tabs : TabLayout = findViewById(R.id.tabs)
        tabs.addTab(tabs.newTab().setText("Pestaña 1"))
        tabs.addTab(tabs.newTab().setText("Pestaña 2"))
        tabs.addTab(tabs.newTab().setText("Pestaña 3"))
        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position){
                    0 -> texto.text = "Pestaña 1"
                    1 -> texto.text = ("Pestaña 2"
                    )
                    2 -> texto.text = ("Pestaña 3"
                    )
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })


    }
}