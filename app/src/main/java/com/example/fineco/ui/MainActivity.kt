package com.example.fineco.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fineco.R
import com.example.fineco.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        val mainScreens = mapOf(
            R.id.sectionsFragment to true,
            R.id.budgetFragment to true,
        )

        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val height = binding.bottomNavigationView.height.toFloat()
            if (destination.id in mainScreens) {
                binding.bottomNavigationView.animate().translationY(0f).duration = 300
            } else {
                binding.bottomNavigationView.animate().translationY(height).duration = 300

            }

        }


    }


}