package com.teamproject.wounddetection.viewmodel

import androidx.lifecycle.ViewModel
import com.teamproject.wounddetection.data.ApiClient
import retrofit2.Call
import javax.security.auth.callback.Callback

class AuthViewModel : ViewModel() {
    fun makeSignInRequest(login: String, password: String) {
        val postId = 1 // Replace with the desired post ID
        val call = ApiClient.userApi.getUser()

        call.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    val post = response.body()
                    // Handle the retrieved post data
                } else {
                    // Handle error
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                // Handle failure
            }
        })
    }
}