package com.noahgeren.trailangel.ui.common

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.noahgeren.trailangel.R

object PreferenceUtils {

    private val apiPrefName ="API_PREFS";
    private val apiAuthToken = "API_AUTH_TOKEN";

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

    private fun loadSharedPref(context: Context) {
        if (sharedPref == null) {
            sharedPref =  context.getSharedPreferences(apiPrefName, Context.MODE_PRIVATE)
        }
    }

}