package com.sagar.rwelocme.domain.repository

import com.sagar.rwelocme.api.ApiService
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.ProfileRequest
import com.sagar.rwelocme.domain.model.UploadProfileResponse
import com.sagar.rwelocme.domain.model.UserProfileResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UploadProfileRepository  @Inject constructor(
    private val api: ApiService
) {

    suspend fun uploadImage(file: File, token: String): NetworkResult<UploadProfileResponse> {
        return try {

            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

            val body = MultipartBody.Part.createFormData(
                "file",   // 🔥 must match API key name
                file.name,
                requestFile
            )
            var apiKey = "sk_live_aaecfebe1c4baab87a1eba0a608f4439e6f85a2e0a18f206"
            val response = api.uploadImage(body, apiKey,token)

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Upload failed")
        }
    }



    suspend fun uploadProfile(profileRequest: ProfileRequest, token: String): NetworkResult<UserProfileResponse> {
        return try {

             var apiKey = "sk_live_aaecfebe1c4baab87a1eba0a608f4439e6f85a2e0a18f206"
            val response = api.updateProfile(
                profileRequest,
                apiKey,
                "Bearer $token" // ✅ FIX
            )

            if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(response.body()!!)
            } else {
                NetworkResult.Error(response.message())
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Upload failed")
        }
    }



}