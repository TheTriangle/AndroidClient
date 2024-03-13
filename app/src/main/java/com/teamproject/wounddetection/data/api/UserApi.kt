package com.teamproject.wounddetection.data.api

import com.teamproject.wounddetection.data.model.Auth
import com.teamproject.wounddetection.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("/profile")
    suspend fun getUser(): Call<User>

    @POST("/accounts/login/")
    suspend fun login(@Body user: User): Auth
}