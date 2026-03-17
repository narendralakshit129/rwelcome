package com.sagar.rwelocme.domain.usecase

import com.sagar.rwelocme.domain.repository.AuthRepository
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.getCountries()
}