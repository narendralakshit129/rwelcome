package com.sagar.rwelocme.domain.model

data class VerifyOtpRequest(
    val mobile: String,
    val otp: String
)
