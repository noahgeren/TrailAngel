package com.noahgeren.trailangel.api

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.noahgeren.trailangel.database.EmergencyContactRepository
import com.noahgeren.trailangel.database.HikeRepository
import com.noahgeren.trailangel.database.ParkRepository
import com.noahgeren.trailangel.database.TrailRepository
import com.noahgeren.trailangel.models.*
import com.noahgeren.trailangel.ui.common.PreferenceUtils
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.Executors

object ApiService {

    private val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.155:8080/") // This should be your local ip address for development
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerModule(JavaTimeModule())))
                .build()
    private val apiRepository = retrofit.create(ApiRepository::class.java)
    private val executor = Executors.newSingleThreadExecutor()

    fun requestLogin(user: User, callback: Callback<Map<String, Boolean>>?) {
        apiRepository.requestLogin(user).enqueue(createCallback(callback))
    }

    fun login(verification: Verification, callback: Callback<Map<String, Any>>?) {
        apiRepository.login(verification).enqueue(createCallback(callback))
    }

    fun updateAccount(user: User, callback: Callback<User>? = null) {
        apiRepository.updateUser(user).enqueue(createCallback(callback, object: Callback<User>() {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let {
                    PreferenceUtils.setName(it.name)
                    PreferenceUtils.setTrailName(it.trailName)
                }
            }
        }))
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

    fun getHikes(callback: Callback<List<Hike>>? = null) {
        apiRepository.getHikes().enqueue(createCallback(callback, object: Callback<List<Hike>>() {
            override fun onResponse(call: Call<List<Hike>>, response: Response<List<Hike>>) {
                response.body()?.let {
                    HikeRepository.get().insertHikes(it)
                }
            }
        }))
    }

    fun saveHike(hike: Hike, callback: Callback<Hike>? = null) {
        apiRepository.saveHike(hike).enqueue(createCallback(callback, object: Callback<Hike>() {
            override fun onResponse(call: Call<Hike>, response: Response<Hike>) {
                response.body()?.let {
                    HikeRepository.get().insertHike(it)
                }
            }
        }))
    }

    fun getContacts(callback: Callback<List<EmergencyContact>>? = null) {
        apiRepository.getContacts().enqueue(createCallback(callback, object: Callback<List<EmergencyContact>>() {
            override fun onResponse(call: Call<List<EmergencyContact>>, response: Response<List<EmergencyContact>>) {
                response.body()?.let {
                    EmergencyContactRepository.get().insertContacts(it)
                }
            }
        }))
    }

    fun saveContact(contact: EmergencyContact, callback: Callback<EmergencyContact>? = null) {
        apiRepository.saveContact(contact).enqueue(createCallback(callback, object: Callback<EmergencyContact>() {
            override fun onResponse(call: Call<EmergencyContact>, response: Response<EmergencyContact>) {
                response.body()?.let {
                    EmergencyContactRepository.get().insertContact(contact)
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