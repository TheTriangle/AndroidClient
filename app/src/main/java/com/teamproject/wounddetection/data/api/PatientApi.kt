package com.teamproject.wounddetection.data.api

import com.teamproject.wounddetection.data.model.Case
import com.teamproject.wounddetection.data.model.CasePost
import com.teamproject.wounddetection.data.model.Patient
import com.teamproject.wounddetection.data.model.Profile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface PatientApi {
    @GET("/getPatient/{code}")
    suspend fun getPatient(@Path("code") code: String): Patient

    @POST("/cases")
    suspend fun addCase(@Body case: CasePost): Case

    @GET("/accounts/profile")
    suspend fun getProfile(): Profile

    @Multipart
    @POST("/wounds")
    suspend fun uploadWound(
        @Part("case") case: RequestBody,
        @Part("upload_date") uploadDate: RequestBody,
        @Part("depth") depth: RequestBody,
        @Part("category") category: RequestBody,
        @Part("type") type: RequestBody,
        @Part("area") area: RequestBody,
        @Part("diameter") diameter: RequestBody,
        @Part("additional") additional: RequestBody,
        @Part("image_url") img: MultipartBody.Part
    )
}
