package com.sagar.rwelocme.domain.repository

import com.sagar.rwelocme.api.ApiService
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.OtpRequest
import com.sagar.rwelocme.domain.model.OtpResponse
import com.sagar.rwelocme.domain.model.VerifyOtpRequest
import com.sagar.rwelocme.domain.model.VerifyOtpResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: ApiService
) : AuthRepository {

    override suspend fun requestOtp(mobile: String): NetworkResult<OtpResponse> {

        return try {

            val response = api.requestOtp(OtpRequest(mobile))

            if (response.isSuccessful) {

                NetworkResult.Success(response.body()!!)

            } else {

                NetworkResult.Error(response.message())
            }

        } catch (e: Exception) {

            NetworkResult.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun verifyOtp(request: VerifyOtpRequest): NetworkResult<VerifyOtpResponse> {

        return try {
            val response = api.verifyOtp(request)

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message()) // 🔥 important
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Network Error")
        }
    }
}