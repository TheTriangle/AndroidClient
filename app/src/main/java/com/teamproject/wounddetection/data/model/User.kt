package com.teamproject.wounddetection.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username") var login: String,
    @SerializedName("password") var password: String
)