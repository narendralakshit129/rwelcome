package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hbb20.CountryCodePicker
import com.sagar.rwelocme.MainActivity
import com.sagar.rwelocme.R
import com.sagar.rwelocme.comman.Gender
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.dialog.GalleryCameraBottomSheet
import com.sagar.rwelocme.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInWithOtpActivity : AppCompatActivity() {
    private lateinit var ivBack: ImageView
    private lateinit var etMobile: EditText
    private lateinit var ccp: CountryCodePicker
    private lateinit var btnSendOtp: Button
    private lateinit var tvHavePassword: TextView
    private lateinit var progressBar: ProgressBar

    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_with_otp)

        initViews()
        setupClicks()
        setupCountryPicker()
        observeOtpState()

    }

    private fun initViews() {

        ivBack = findViewById(R.id.iv_back)

        etMobile = findViewById(R.id.et_mobile)
        ccp = findViewById(R.id.ccp)

        btnSendOtp = findViewById(R.id.btn_send_otp)
        tvHavePassword = findViewById(R.id.txt_have_password)
        progressBar = findViewById(R.id.progress_bar)

    }

    private fun setupCountryPicker() {
        ccp.registerCarrierNumberEditText(etMobile)
        ccp.setOnCountryChangeListener {
            etMobile.text.clear()
        }
        progressBar.visibility = View.GONE
    }

    private fun setupClicks() {

        ivBack.setOnClickListener { finish() }

        tvHavePassword.setOnClickListener { finish() }

        btnSendOtp.setOnClickListener {

            btnSendOtp.isEnabled = false

            if (validateInput()) {
                viewModel.requestOtp(ccp.fullNumberWithPlus)
            }

            btnSendOtp.postDelayed({
                btnSendOtp.isEnabled = true
            }, 1000)

        }
    }

    private fun validateInput(): Boolean {
        return if (!ccp.isValidFullNumber) {
            etMobile.error = "Invalid phone number"
            false
        } else {
            true
        }
    }


    private fun observeOtpState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.otpState.collect { result ->

                    when (result) {

                        NetworkResult.Idle -> {
                            showLoading(false)
                        }

                        is NetworkResult.Loading -> {
                            showLoading(true)
                        }

                        is NetworkResult.Success -> {
                            Log.d("API", "Success: ${result.data},${result.data.otp}")
                            showLoading(false)
                            navigateToOtpScreen()
                        }

                        is NetworkResult.Error -> {
                            showLoading(false)
                            showError(result.message)
                        }
                    }
                }
            }
        }
    }

    // -------------------- UI HELPERS --------------------

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        btnSendOtp.isEnabled = !show
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message ?: "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToOtpScreen() {
        val intent = Intent(this, OtpVerificationActivity::class.java).apply {
            putExtra("KEY_COMMING_FROM", "SIGN_IN_OTP")
            putExtra("KEY_MOBILE_NUMBER", ccp.fullNumberWithPlus)
        }
        startActivity(intent)
        finish()
    }

}