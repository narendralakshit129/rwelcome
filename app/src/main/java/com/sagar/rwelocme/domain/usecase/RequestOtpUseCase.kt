package com.sagar.rwelocme.domain.usecase

import com.sagar.rwelocme.domain.repository.AuthRepository

import javax.inject.Inject

class RequestOtpUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(mobile: String) =
        repository.requestOtp(mobile)

}