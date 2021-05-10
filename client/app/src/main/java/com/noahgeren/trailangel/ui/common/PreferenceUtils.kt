package com.noahgeren.trailangel.ui.common

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.noahgeren.trailangel.R

object PreferenceUtils {

    private val prefName ="TRAIL_ANGEL_PREFS"
    private val apiAuthToken = "API_AUTH_TOKEN"
    private val userName = "USER_NAME"
    private val userTrailName = "USER_TRAIL_NAME"

    private var sharedPref: SharedPreferences? = null

    fun init(context: Context) {
        loadSharedPref(context)
    }

    fun getToken(): String? {
        return sharedPref?.getString(apiAuthToken, null)
    }

    fun setToken(value: String?) {
        sharedPref?.let {
            it.edit().putString(apiAuthToken, value).apply()
        }
    }

    fun setName(value: String?) {
        sharedPref?.let {
            it.edit().putString(userName, value).apply()
        }
    }

    fun setTrailName(value: String?) {
        sharedPref?.let {
            it.edit().putString(userTrailName, value).apply()
        }
    }

    fun getName(): String? {
        return sharedPref?.getString(userName, null)
    }

    fun getTrailName(): String? {
        return sharedPref?.getString(userTrailName, null)
    }

    private fun loadSharedPref(context: Context) {
        if (sharedPref == null) {
            sharedPref =  context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        }
    }

}