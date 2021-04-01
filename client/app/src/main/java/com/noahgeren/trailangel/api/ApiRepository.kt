package com.noahgeren.trailangel.api

import com.noahgeren.trailangel.models.Park
import com.noahgeren.trailangel.models.Trail
import com.noahgeren.trailangel.models.User
import com.noahgeren.trailangel.models.Verification
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

}