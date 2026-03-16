package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.hbb20.CountryCodePicker
import com.sagar.rwelocme.R
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInWithOtpActivity : AppCompatActivity() {

    private lateinit var btnSendOtp: Button
    private lateinit var tvHavePassword: TextView
    private lateinit var etMobile: EditText
    private lateinit var ccp: CountryCodePicker
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_with_otp)


        etMobile = findViewById(R.id.et_mobile)
        ccp = findViewById(R.id.ccp)

        btnSendOtp = findViewById(R.id.btn_send_otp)
        tvHavePassword = findViewById(R.id.txt_have_password)

        btnSendOtp.setOnClickListener {



            lifecycleScope.launch {

                viewModel.otpState.collect { result ->

                    when (result) {

                        is NetworkResult.Loading -> {
                            Log.d("API", "Loading")
                        }

                        is NetworkResult.Success -> {
                            Log.d("API", "Success: ${result.data}")
                            val intent = Intent(this@SignInWithOtpActivity, OtpVerificationActivity::class.java)
                            intent.putExtra("KEY_COMMING_FROM", "SIGN_IN_OTP")
                            startActivity(intent)
                            finish()
                        }

                        is NetworkResult.Error -> {
                            Log.d("API", "Error: ${result.message}")
                        }
                    }
                }
            }

            viewModel.requestOtp("+919599781020")

        }

        tvHavePassword.setOnClickListener {
            finish()
        }


    }
}