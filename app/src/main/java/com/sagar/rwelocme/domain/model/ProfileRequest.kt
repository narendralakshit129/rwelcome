package com.sagar.rwelocme.domain.model

data class ProfileRequest(

    val profileImage: String,
    val firstName: String,
    val lastName: String,
    val address: String,
    val displayName: String,
    val gender: String,
    val countryId: Int,
    val bio: String
)
