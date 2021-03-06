package com.noahgeren.trailangel.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.ui.common.SharedViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = (supportFragmentManager.findFragmentById(R.id.main_fragment_view) as NavHostFragment).navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_parks, R.id.navigation_schedule, R.id.navigation_settings))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_fragment_view)
        when(navController.currentDestination?.id) {
            R.id.navigation_parks -> finish()
            R.id.navigation_trails -> sharedViewModel.trailsState = SharedViewModel.PARKS
            R.id.navigation_trail_details -> sharedViewModel.trailsState = SharedViewModel.TRAILS
        }
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}