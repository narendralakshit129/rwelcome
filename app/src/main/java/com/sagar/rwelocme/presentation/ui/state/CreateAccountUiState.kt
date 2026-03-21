package com.sagar.rwelocme.presentation.ui.state

import android.net.Uri
import com.sagar.rwelocme.comman.Gender
import com.sagar.rwelocme.domain.model.Country

data class CreateAccountUiState(

   // Country list from API
    val countries: List<Country> = emptyList(),

    // Selected country
    val selectedCountry: String = "",
    val selectedCountryId: Int = -1,
    val selectedCountryCode: String = "",

    // Selected gender
    val selectedGender: Gender? = null,

    // Selected image
    val imageUri: Uri? = null,

    // Loader
    val isLoading: Boolean = false,

    // Error message
    val error: String? = null)
