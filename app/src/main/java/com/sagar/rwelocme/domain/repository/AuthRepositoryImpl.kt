package com.sagar.rwelocme.domain.repository

import com.sagar.rwelocme.api.ApiService
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.Country
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
            var apiKey = "sk_live_aaecfebe1c4baab87a1eba0a608f4439e6f85a2e0a18f206"
            val response = api.requestOtp(apiKey,OtpRequest(mobile))

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
            var apiKey = "sk_live_aaecfebe1c4baab87a1eba0a608f4439e6f85a2e0a18f206"
            val response = api.verifyOtp(apiKey,request)

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message()) // 🔥 important
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Network Error")
        }
    }

    override suspend fun getCountries(): NetworkResult<List<Country>> {

        return try {
            var apiKey = "sk_live_aaecfebe1c4baab87a1eba0a608f4439e6f85a2e0a18f206"
            val response = api.getCountries(apiKey)

            if (response.isSuccessful) {
                NetworkResult.Success(response.body()?.countries ?: emptyList())
            } else {
                NetworkResult.Error(response.message())
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Error")
        }
    }
}