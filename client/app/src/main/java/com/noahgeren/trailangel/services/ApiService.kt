package com.noahgeren.trailangel.services

import com.noahgeren.trailangel.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/user/login/request")
    fun requestLogin(@Body user: User): Call<Map<String, Boolean>>

}