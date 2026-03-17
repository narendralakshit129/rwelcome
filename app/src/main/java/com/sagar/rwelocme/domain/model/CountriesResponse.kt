package com.sagar.rwelocme.domain.model

data class CountriesResponse(
    val countries: List<Country>
)

data class Country(
    val id: Int,
    val code: String,
    val name: String
)