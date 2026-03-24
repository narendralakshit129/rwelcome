package com.sagar.rwelocme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonObject
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.repository.RoomRepository
import com.sagar.rwelocme.model.network_response.RoomDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: RoomRepository
) : ViewModel() {

    val roomName = MutableStateFlow("")
    val roomDescription = MutableStateFlow("")
    val roomType = MutableStateFlow("public") // default
    var roomImageKey: String = ""

    private val _rooms = MutableStateFlow<List<RoomDto>>(emptyList())
    val rooms: StateFlow<List<RoomDto>> = _rooms



    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isSuccess = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess


    fun createRoom(token: String) {

        if (roomName.value.isEmpty()) {
            _error.value = "Room name required"
            return
        }

        val request = JsonObject().apply {
            addProperty("name", roomName.value)
            addProperty("description", roomDescription.value)
            addProperty("type", roomType.value)
            addProperty("roomImage", roomImageKey) // 👈 important
        }

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            when (val result = repository.createRoom(request, token)) {

                is NetworkResult.Success -> {
                    _isLoading.value = false
                    _isSuccess.value = true
                }

                is NetworkResult.Error -> {
                    _isLoading.value = false
                    _error.value = result.message
                }

                else -> {}
            }
        }
    }

    fun getRooms(token: String) {
        viewModelScope.launch {

            _isLoading.value = true

            when (val result = repository.getRooms(token)) {

                is NetworkResult.Success -> {
                    _isLoading.value = false
                    _rooms.value = result.data
                }

                is NetworkResult.Error -> {
                    _isLoading.value = false
                    _error.value = result.message
                }

                else -> {}
            }
        }

    }
    fun resetSuccess() {
        _isSuccess.value = false
    }
}