package com.teamproject.wounddetection.data.api

import com.teamproject.wounddetection.data.model.Patient
import retrofit2.http.GET
import retrofit2.http.Path

interface PatientApi {
    @GET("/getPatient/{code}")
    suspend fun getPatient(@Path("code") code: String): Patient
}
