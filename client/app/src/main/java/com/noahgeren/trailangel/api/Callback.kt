package com.noahgeren.trailangel.api

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "CALLBACK"

abstract class Callback<T> : Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {}

    override fun onFailure(call: Call<T>, t: Throwable) {
        Log.d(TAG, t.message ?: "")
    }

    open fun onComplete(call: Call<T>) {}

}