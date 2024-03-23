package com.teamproject.wounddetection.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Patient(
    val id: Int,
    val name: String,
    val mail: String,
    val cases: List<Case>
) : Parcelable
