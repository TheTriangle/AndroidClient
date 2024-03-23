package com.teamproject.wounddetection.data.api.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class AddAuthHeadersInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        if (!chain.request().url.toString().contains("accounts") || chain.request().url.toString().contains("profile")) {
            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            val preferences = EncryptedSharedPreferences.create(
                "AUTH",
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            val token = preferences.getString("token", "")
            builder.addHeader("Authorization", "Token $token")
        }
        return chain.proceed(builder.build())
    }
}