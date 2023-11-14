package com.teamproject.wounddetection.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://localhost:8000/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

object ApiClient {
    val userApi: UserApi by lazy {
        RetrofitClient.retrofit.create(UserApi::class.java)
    }
}