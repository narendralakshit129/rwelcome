package com.sagar.rwelocme.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
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
import com.sagar.rwelocme.MainActivity
import com.sagar.rwelocme.R
import com.sagar.rwelocme.Utils.StorePref
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import javax.inject.Inject
import kotlin.getValue

@AndroidEntryPoint
class OtpVerificationActivity : AppCompatActivity() {


    private lateinit var ivBack: ImageView
    private lateinit var tvMobileNumber: TextView
    private lateinit var otpFields: List<EditText>
    private lateinit var btnVerifyOtp: Button
    private lateinit var progressBar: ProgressBar

    private var comingFrom: String = ""
    private var mobileNumber: String = ""

    @Inject
    lateinit var storePref: StorePref

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otp_verification)

        initViews()
        setupOtpInputs()
        setupClicks()
        observeOtpState()
        handleIntent()

    }


    private fun initViews() {
        ivBack = findViewById(R.id.iv_back)
        tvMobileNumber = findViewById(R.id.tv_mobile_number)

        otpFields = listOf(
            findViewById(R.id.otp1),
            findViewById(R.id.otp2),
            findViewById(R.id.otp3),
            findViewById(R.id.otp4),
            findViewById(R.id.otp5),
            findViewById(R.id.otp6)
        )

        btnVerifyOtp = findViewById(R.id.btn_submit_otp)
        progressBar = findViewById(R.id.progress_bar)

        progressBar.visibility = View.GONE
    }

    private fun handleIntent() {
        comingFrom = intent.getStringExtra("KEY_COMMING_FROM") ?: ""
        mobileNumber = intent.getStringExtra("KEY_MOBILE_NUMBER") ?: ""

        tvMobileNumber.text =
            "A 6 digit code has been sent to ${maskMobile(mobileNumber)}"
    }


    private fun setupOtpInputs() {
        otpFields.forEachIndexed { index, editText ->

            // Move next
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun afterTextChanged(s: Editable?) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && index < otpFields.size - 1) {
                        otpFields[index + 1].requestFocus()
                    }

                    // Auto submit when last digit entered
                    if (index == otpFields.lastIndex && getOtp().length == 6) {
                        verifyOtp()
                    }
                }
            })

            // Move previous
            editText.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL &&
                    editText.text.isEmpty() &&
                    index > 0
                ) {
                    otpFields[index - 1].requestFocus()
                }
                false
            }
        }
    }

    private fun getOtp(): String {
        return otpFields.joinToString("") { it.text.toString() }
    }

    private fun setupClicks() {
        ivBack.setOnClickListener { finish() }

        btnVerifyOtp.setOnClickListener {
            verifyOtp()
        }
    }

    private fun verifyOtp() {
        val otp = getOtp()

        if (otp.length != 6) {
            showError("Please enter valid 6 digit OTP")
            return
        }

        viewModel.verifyOtp(mobileNumber, otp)
    }

    private fun observeOtpState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.otpVerifyState.collect { result ->

                    when (result) {

                        NetworkResult.Idle -> showLoading(false)

                        is NetworkResult.Loading -> showLoading(true)

                        is NetworkResult.Success -> {
                            showLoading(false)
                            Log.d("API", "Success: ${result.data}")
                            val response = result.data
                            lifecycleScope.launch {
                                storePref.setUserToken(response.token)
                                storePref.setUserId(response.user.id.toString())
                                storePref.setUserMobile(response.user.mobile ?: "")
                                storePref.setUserName(response.user.firstName ?: "")
                            }
                            navigateToScreen(response.user.firstName ?: "")
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


    private fun maskMobile(number: String): String {
        return if (number.length >= 10) {
            val prefix = number.take(number.length-4)
            "$prefix****"
        } else number
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        btnVerifyOtp.isEnabled = !show
    }

    private fun showError(message: String?) {
        Toast.makeText(this, message ?: "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    private fun navigateToScreen(userName:String) {
        when (comingFrom) {
            "FORGOT_PASSWORD" -> {
                startActivity(Intent(this, CreatePassword::class.java))
            }
            "SIGN_IN_OTP" -> {
                if(userName.isEmpty()){
                    startActivity(Intent(this, CreateAccount::class.java))
                }else{
                    lifecycleScope.launch {
                        storePref.setLoginStatus(true)
                    }

                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)

                }
            }
        }
        finish()
    }



}