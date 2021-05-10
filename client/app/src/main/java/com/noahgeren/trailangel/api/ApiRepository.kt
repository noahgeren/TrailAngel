package com.noahgeren.trailangel.api

import com.noahgeren.trailangel.models.*
import com.noahgeren.trailangel.ui.common.PreferenceUtils
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiRepository {

    @POST("/user/login/request")
    fun requestLogin(@Body user: User): Call<Map<String, Boolean>>

    @POST("/user/login")
    fun login(@Body verification: Verification): Call<Map<String, Any>>

    @GET("/parks/all")
    fun getParks(@Header("Authorization") token: String = "Bearer " + PreferenceUtils.getToken()): Call<List<Park>>

    @GET("/trails/all")
    fun getTrails(@Header("Authorization") token: String = "Bearer " + PreferenceUtils.getToken()): Call<List<Trail>>

    @GET("/hike/all")
    fun getHikes(@Header("Authorization") token: String = "Bearer " + PreferenceUtils.getToken()): Call<List<Hike>>

    @POST("/hike/save")
    fun saveHike(@Body hike: Hike, @Header("Authorization") token: String = "Bearer " + PreferenceUtils.getToken()): Call<Hike>

    @GET("/contact/all")
    fun getContacts(@Header("Authorization") token: String = "Bearer " + PreferenceUtils.getToken()): Call<List<EmergencyContact>>

    @POST("/contact/save")
    fun saveContact(@Body contact: EmergencyContact, @Header("Authorization") token: String = "Bearer " + PreferenceUtils.getToken()): Call<EmergencyContact>

}