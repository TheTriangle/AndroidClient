package com.teamproject.wounddetection.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Case(
    val id: Int,
    val doctor: Doctor,
    val reports: List<WoundReport>
) : Parcelable

@Parcelize
data class WoundReport(
    val id: Int,
    val depth: String,
    val woundClass: String,
    val type: String,
    val area: Double,
    val diameter: Double,
    val rotPercentage: Double,
    val photoUrl: String,
) : Parcelable