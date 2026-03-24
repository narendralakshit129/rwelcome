package com.sagar.rwelocme.domain.repository

import com.google.gson.JsonObject
import com.sagar.rwelocme.api.ApiService
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.model.network_response.RoomDto
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val api: ApiService
) {
    var apiKey = "sk_live_aaecfebe1c4baab87a1eba0a608f4439e6f85a2e0a18f206"
    suspend fun createRoom(
        request: JsonObject,
        token: String
    ): NetworkResult<Any?> {
        return try {

            val response = api.createRoom(
                apiKey = apiKey,
                token = "Bearer $token",
                request = request
            )

            if (response.isSuccessful) {
                NetworkResult.Success(response.body())
            } else {
                NetworkResult.Error(response.message())
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Something went wrong")
        }
    }


    suspend fun getRooms(token: String): NetworkResult<List<RoomDto>> {
        return try {
            val response = api.getRooms(
                apiKey = apiKey,
                token = "Bearer $token"
            )

            if (response.isSuccessful) {
                NetworkResult.Success(response.body()?.rooms ?: emptyList())
            } else {
                NetworkResult.Error(response.message())
            }

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Error fetching rooms")
        }
    }
}