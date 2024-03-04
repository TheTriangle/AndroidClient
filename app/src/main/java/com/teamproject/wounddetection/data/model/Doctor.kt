package com.teamproject.wounddetection.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Doctor(
    val id: String,
    val email: String,
    val specialization: String,
) : Parcelable