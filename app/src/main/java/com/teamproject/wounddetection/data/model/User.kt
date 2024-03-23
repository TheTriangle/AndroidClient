package com.teamproject.wounddetection.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("login") var login: String,
    @SerializedName("password") var password: String,
    @SerializedName("password_confirm") var passwordConfirm: String,
)

data class Profile(
    @SerializedName("id") val id: Int,
    @SerializedName("username") val username: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("email") val email: String
)