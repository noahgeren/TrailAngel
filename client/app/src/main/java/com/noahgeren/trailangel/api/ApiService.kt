package com.noahgeren.trailangel.api

import android.util.Log
import com.noahgeren.trailangel.database.ParkRepository
import com.noahgeren.trailangel.database.TrailRepository
import com.noahgeren.trailangel.models.Park
import com.noahgeren.trailangel.models.Trail
import com.noahgeren.trailangel.models.User
import com.noahgeren.trailangel.models.Verification
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors

object ApiService {

    private val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.155:8080/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
    private val apiRepository = retrofit.create(ApiRepository::class.java)
    private val executor = Executors.newSingleThreadExecutor()

    fun requestLogin(user: User, callback: Callback<Map<String, Boolean>>?) {
        apiRepository.requestLogin(user).enqueue(createCallback(callback))
    }

    fun login(verification: Verification, callback: Callback<Map<String, Any>>?) {
        apiRepository.login(verification).enqueue(createCallback(callback))
    }

    fun getParks(callback: Callback<List<Park>>? = null) {
        apiRepository.getParks().enqueue(createCallback(callback, object: Callback<List<Park>>() {
            override fun onResponse(call: Call<List<Park>>, response: Response<List<Park>>) {
                response.body()?.let {
                    ParkRepository.get().insertParks(it)
                }
            }
        }))
    }

    fun getTrails(callback: Callback<List<Trail>>? = null) {
        apiRepository.getTrails().enqueue(createCallback(callback, object: Callback<List<Trail>>() {
            override fun onResponse(call: Call<List<Trail>>, response: Response<List<Trail>>) {
                response.body()?.let {
                    TrailRepository.get().insertTrails(it)
                }
            }
        }))
    }

    private fun <T> createCallback(customCallback: Callback<T>? = null, serviceCallback: Callback<T>? = null): Callback<T> {
        return object: Callback<T>() {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                serviceCallback?.onResponse(call, response)
                customCallback?.onResponse(call, response)
                serviceCallback?.onComplete(call)
                customCallback?.onComplete(call)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                serviceCallback?.onFailure(call, t)
                customCallback?.onFailure(call, t)
                serviceCallback?.onComplete(call)
                customCallback?.onComplete(call)
            }

        }
    }

}