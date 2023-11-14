package com.teamproject.wounddetection

import android.app.Application
import com.teamproject.wounddetection.data.api.utils.AddAuthHeadersInterceptor
import com.teamproject.wounddetection.data.api.utils.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: change to https protocol
private const val BASE_URL = "http://10.0.2.2:8000/"

class App : Application() {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(ReceivedCookiesInterceptor(this))
                    .addInterceptor(AddAuthHeadersInterceptor(this))
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}