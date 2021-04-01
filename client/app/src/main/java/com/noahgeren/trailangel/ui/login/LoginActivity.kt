package com.noahgeren.trailangel.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.ui.common.PreferenceUtils

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = (supportFragmentManager.findFragmentById(R.id.login_fragment_view) as NavHostFragment).navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_login))
        setupActionBarWithNavController(navController, appBarConfiguration)

        PreferenceUtils.init(this)
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.login_fragment_view)
        when(navController.currentDestination?.id) {
            R.id.navigation_login-> finish()
        }
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}