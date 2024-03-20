package com.teamproject.wounddetection.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Case(
    val id: Int,
    val doctor: Doctor,
    val reports: List<WoundReport>,
    val date: String?,
) : Parcelable

@Parcelize
data class CasePost(
    val doctor: Int,
    val patient: Int,
    val reports: List<WoundReport> = listOf(),
    val date: String,
) : Parcelable

@Parcelize
data class WoundReport(
    val id: Int,
    val depth: String,
    val category: String,
    val type: String,
    val area: String,
    val diameter: String,
    val additional: String,
    @SerializedName("image_url") val photoUrl: String,
) : Parcelable