package com.sagar.rwelocme.api

import com.sagar.rwelocme.domain.model.OtpRequest
import com.sagar.rwelocme.domain.model.OtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/otp/request")
    suspend fun requestOtp(
        @Body request: OtpRequest
    ): Response<OtpResponse>

}