package com.teamproject.wounddetection.data.api.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpCookie


private const val TAG = "ReceivedCookiesInterceptor"

class ReceivedCookiesInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = mutableMapOf<String, String>()

            for (header in originalResponse.headers("Set-Cookie")) {
                val currentCookie = HttpCookie.parse(header)
                cookies[currentCookie[0].name] = currentCookie[0].value
            }

            val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
            val preferences = EncryptedSharedPreferences.create(
                "COOKIES",
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
            val editor = preferences.edit()
            for (key in cookies.keys) {
                editor.putString(key, cookies[key])
            }
            editor.apply()
        }
        return originalResponse
    }
}