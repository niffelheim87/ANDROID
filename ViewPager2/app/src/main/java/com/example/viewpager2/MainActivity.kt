package com.example.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { ViewPagerAdapter(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var pager = findViewById<ViewPager2>(R.id.pager)
        pager.adapter = adapter

    }
}