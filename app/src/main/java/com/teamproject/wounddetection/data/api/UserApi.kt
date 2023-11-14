package com.teamproject.wounddetection.data.api

import com.teamproject.wounddetection.data.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {
    @GET("/profile")
    fun getUser(): Call<User>

    @POST("/login")
    fun login(@Body user: User)
}