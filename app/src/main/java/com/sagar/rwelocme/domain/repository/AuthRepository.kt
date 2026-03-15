package com.sagar.rwelocme.domain.repository

import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.OtpResponse

interface AuthRepository {
    suspend fun requestOtp(mobile: String): NetworkResult<OtpResponse>

}