package com.teamproject.wounddetection.data.api.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AddAuthHeadersInterceptor(private val context: Context) : Interceptor {
    private val cookies = listOf("csrftoken", "sessionid")
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        val preferences = EncryptedSharedPreferences.create(
            "COOKIES",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        var s = ""
        for (cookie in cookies) {
            s += "${cookie}=${preferences.getString(cookie, "")};"
        }
        builder.addHeader("Cookie", s)
        builder.addHeader("X-CSRFToken", preferences.getString("csrftoken", "").toString())
        return chain.proceed(builder.build())
    }
}