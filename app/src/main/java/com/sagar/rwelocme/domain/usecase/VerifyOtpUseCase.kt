package com.sagar.rwelocme.domain.usecase

import com.sagar.rwelocme.domain.model.VerifyOtpRequest
import com.sagar.rwelocme.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyOtpUseCase   @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(mobile: String, otp: String) =
        repository.verifyOtp(VerifyOtpRequest(mobile, otp)
    )
}