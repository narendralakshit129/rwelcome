package com.sagar.rwelocme.domain.model

data class UserProfileResponse(
    val id: Int,
    val email: String?,
    val mobile: String?,
    val provider: String?,
    val providerId: String?,
    val firstName: String,
    val lastName: String,
    val displayName: String,
    val gender: String,
    val countryId: Int,
    val bio: String,
    val interests: String?,
    val address: String,
    val profileImage: String,
    val createdAt: String,
    val updatedAt: String,
    val country: Country
)
