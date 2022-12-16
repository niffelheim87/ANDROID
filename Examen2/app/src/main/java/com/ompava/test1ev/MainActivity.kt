package com.ompava.test1ev


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.ompava.test1ev.databinding.ActivityMainBinding
import com.ompava.test1ev.model.Question

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.Theme_LogoSerra)
        Question.loadquestion(this.baseContext)
        Thread.sleep(2000)
        setTheme(R.style.Theme_Examen)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHosFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHosFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.questionnaireFragment
            )
        )
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }
}