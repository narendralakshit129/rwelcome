package com.sagar.rwelocme.domain.model

data class VerifyOtpResponse(
    val token: String,
    val user: User
)

data class User(
    val id: Int,
    val email: String?,
    val mobile: String,
    val firstName: String?,
    val lastName: String?
)