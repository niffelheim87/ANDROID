package com.example.tabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tabs.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityMainBinding
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
    class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            return when (position) {
                0 -> Tab1()
                1 -> Tab2()
                2 -> Tab3()
                else -> Tab1()
            }
        }
    }
}

