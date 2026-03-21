package com.sagar.rwelocme.domain.repository

import com.sagar.rwelocme.api.ApiService
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.UploadProfileResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UploadProfileRepository  @Inject constructor(
    private val api: ApiService
) {

    suspend fun uploadImage(file: File): NetworkResult<UploadProfileResponse> {
        return try {

            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

            val body = MultipartBody.Part.createFormData(
                "file",   // 🔥 must match API key name
                file.name,
                requestFile
            )

            val response = api.uploadImage(body, "apikkey")

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