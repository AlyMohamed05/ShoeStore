package com.udacity.shoestore.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment)
        val appBarConf = AppBarConfiguration(
            setOf(R.id.loginFragment,R.id.welcomeFragment,R.id.shoeListFragment)
        )
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConf)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}