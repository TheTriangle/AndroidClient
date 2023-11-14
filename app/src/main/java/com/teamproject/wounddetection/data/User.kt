package com.teamproject.wounddetection.data

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("login") var login: String,
    @SerializedName("password") var password: String
)