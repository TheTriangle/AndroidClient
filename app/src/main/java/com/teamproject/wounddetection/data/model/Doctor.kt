package com.teamproject.wounddetection.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    val id: Int,
    val email: String,
    val name: String,
    val specialization: String,
) : Parcelable