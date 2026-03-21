package com.sagar.rwelocme.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagar.rwelocme.comman.Gender
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.ProfileRequest
import com.sagar.rwelocme.domain.model.UploadProfileResponse
import com.sagar.rwelocme.domain.model.UserProfileResponse
import com.sagar.rwelocme.domain.repository.UploadProfileRepository
import com.sagar.rwelocme.domain.usecase.GetCountriesUseCase
import com.sagar.rwelocme.presentation.ui.state.CreateAccountUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject
@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val repository: UploadProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CreateAccountUiState())
    val uiState: StateFlow<CreateAccountUiState> = _uiState

    fun getCountries() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, error = null) }

            when (val result = getCountriesUseCase()) {

                is NetworkResult.Idle -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }

                is NetworkResult.Success -> {
                   // val list = result.data?.map { it.name } ?: emptyList()
                    val list = result.data ?: emptyList()

                    _uiState.update {
                        it.copy(
                            countries = list,
                            isLoading = false
                        )
                    }
                }

                is NetworkResult.Error -> {
                    _uiState.update {
                        it.copy(
                            error = result.message,
                            isLoading = false
                        )
                    }
                }

                is NetworkResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun selectGender(gender: Gender) {
        _uiState.update { it.copy(selectedGender = gender) }
    }


    fun selectCountry(id: Int, countryName: String, countryCode: String) {
        _uiState.update {
            it.copy(
                selectedCountry = countryName,
                selectedCountryId = id,
                selectedCountryCode = countryCode
            )
        }
    }

    fun setImage(uri: Uri) {
        _uiState.update { it.copy(imageUri = uri) }
    }


    private val _uploadState = MutableStateFlow<NetworkResult<UploadProfileResponse>>(NetworkResult.Idle)
    val uploadState: StateFlow<NetworkResult<UploadProfileResponse>> = _uploadState

    fun uploadImage(file: File, token: String) {
        viewModelScope.launch {
            _uploadState.value = NetworkResult.Loading
            _uploadState.value = repository.uploadImage(file, token)
        }
    }


    private val _uploadProfileState = MutableStateFlow<NetworkResult<UserProfileResponse>>(NetworkResult.Idle)
    val uploadProfileState: StateFlow<NetworkResult<UserProfileResponse>> = _uploadProfileState

    fun createProfile(
        profileImage: String,
        firstName: String,
        lastName: String,
        address: String,
        displayName: String,
        gender: Gender?,
        countryId: Int,
        bio: String,
        token: String
    ) {
        viewModelScope.launch {
            _uploadProfileState.value = NetworkResult.Loading

            val result = repository.uploadProfile(
                ProfileRequest(
                    profileImage,
                    firstName,
                    lastName,
                    address,
                    displayName,
                    gender.toString(),
                    countryId,
                    bio
                ),
                token
            )

            _uploadProfileState.value = result
        }
    }


}