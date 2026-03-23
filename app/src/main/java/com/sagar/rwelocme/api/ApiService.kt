package com.sagar.rwelocme.api

import com.sagar.rwelocme.domain.model.CountriesResponse
import com.sagar.rwelocme.domain.model.OtpRequest
import com.sagar.rwelocme.domain.model.OtpResponse
import com.sagar.rwelocme.domain.model.ProfileRequest
import com.sagar.rwelocme.domain.model.UploadProfileResponse
import com.sagar.rwelocme.domain.model.UserProfileResponse
import com.sagar.rwelocme.domain.model.VerifyOtpRequest
import com.sagar.rwelocme.domain.model.VerifyOtpResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface ApiService {
    @POST("auth/otp/request")
    suspend fun requestOtp(
        @Body request: OtpRequest
    ): Response<OtpResponse>


    @POST("auth/otp/verify")
    suspend fun verifyOtp(
        @Body request: VerifyOtpRequest
    ): Response<VerifyOtpResponse>

    @GET("countries")
    suspend fun getCountries(): Response<CountriesResponse>

    @Multipart
    @POST("uploads/file")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
      //  @Header("x-api-key") apiKey: String,
        @Header("Authorization") token: String
    ): Response<UploadProfileResponse>

    @PUT("profile")
    suspend fun updateProfile(
        @Body request: ProfileRequest,
      //  @Header("x-api-key") apiKey: String,
        @Header("Authorization") token: String
    ): Response<UserProfileResponse>


}