package com.example.pokeapi.presentation

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.pokeapi.R
import com.example.pokeapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private lateinit var appBarConfiguration: AppBarConfiguration
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navController = findNavController(R.id.nav_host_fragment_container)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_evolutionsFragment, R.id.nav_pokemonsFragment
            ), drawerLayout
        )
        setSupportActionBar(binding.appBarMain.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        with(binding.navView) {
            setupWithNavController(navController)
            getHeaderView(0).findViewById<TextView>(R.id.textViewName).text
            getHeaderView(0).setOnClickListener {
                Toast.makeText(this@MainActivity, "header touched", Toast.LENGTH_LONG).show()
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.nav_pokemonFragment) {
                this.supportActionBar?.hide()
            } else {
                this.supportActionBar?.show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}