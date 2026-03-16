package com.sagar.rwelocme.domain.repository

import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.OtpResponse
import com.sagar.rwelocme.domain.model.VerifyOtpRequest
import com.sagar.rwelocme.domain.model.VerifyOtpResponse

interface AuthRepository {
    suspend fun requestOtp(mobile: String): NetworkResult<OtpResponse>

    suspend fun verifyOtp(request: VerifyOtpRequest): NetworkResult<VerifyOtpResponse>

}