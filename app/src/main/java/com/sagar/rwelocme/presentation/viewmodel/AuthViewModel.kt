package com.sagar.rwelocme.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.domain.model.OtpResponse
import com.sagar.rwelocme.domain.model.VerifyOtpResponse
import com.sagar.rwelocme.domain.usecase.GetCountriesUseCase
import com.sagar.rwelocme.domain.usecase.RequestOtpUseCase
import com.sagar.rwelocme.domain.usecase.VerifyOtpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val requestOtpUseCase: RequestOtpUseCase,
    private val verifyOtpUseCase: VerifyOtpUseCase,
) : ViewModel() {

    // 🔹 Request OTP
    private val _otpState = MutableStateFlow<NetworkResult<OtpResponse>>(NetworkResult.Idle)
    val otpState: StateFlow<NetworkResult<OtpResponse>> = _otpState

    fun requestOtp(mobile: String) {
        if (_otpState.value is NetworkResult.Loading) return
        viewModelScope.launch {
            _otpState.value = NetworkResult.Loading
            _otpState.value = requestOtpUseCase(mobile)
        }
    }



    // 🔹 Verify OTP
    private val _otpVerifyState =
        MutableStateFlow<NetworkResult<VerifyOtpResponse>>(NetworkResult.Idle)
    val otpVerifyState: StateFlow<NetworkResult<VerifyOtpResponse>> =
        _otpVerifyState


    fun verifyOtp(mobile: String, otp: String) {

        viewModelScope.launch {

            _otpVerifyState.value = NetworkResult.Loading

            _otpVerifyState.value = verifyOtpUseCase(mobile, otp)
        }
    }


}