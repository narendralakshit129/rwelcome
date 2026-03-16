package com.sagar.rwelocme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.OtpResponse
import com.sagar.rwelocme.domain.usecase.RequestOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val requestOtpUseCase: RequestOtpUseCase
) : ViewModel() {

    private val _otpState = MutableStateFlow<NetworkResult<OtpResponse>>(NetworkResult.Loading())
    val otpState: StateFlow<NetworkResult<OtpResponse>> = _otpState

    fun requestOtp(mobile: String) {

        viewModelScope.launch {

            _otpState.value = NetworkResult.Loading()

            _otpState.value = requestOtpUseCase(mobile)

        }
    }
}