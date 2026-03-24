package com.sagar.rwelocme.model.network_response

data class RoomsResponse(
    val rooms: List<RoomDto>
)

data class RoomDto(
    val id: Int,
    val name: String,
    val description: String?,
    val type: String?,
    val roomImage: String?,
    val memberIds: List<Int>
)