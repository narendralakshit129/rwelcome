package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.sagar.rwelocme.R
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.presentation.viewmodel.AuthViewModel
import kotlinx.coroutines.launch
import org.w3c.dom.Text
import kotlin.getValue

class OtpVerificationActivity : AppCompatActivity() {

    private lateinit var btnVerifyOtp: Button
    private lateinit var ivBack: ImageView
    private lateinit var tvMobileNumber: TextView
    private lateinit var otp1: EditText
    private lateinit var otp2: EditText
    private lateinit var otp3: EditText
    private lateinit var otp4: EditText
    private lateinit var otp5: EditText
    private lateinit var otp6: EditText

    var commingFromValue: String ="";
    var mobileNumberValue: String ="";

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otp_verification)

        btnVerifyOtp = findViewById(R.id.btn_submit_otp)
        ivBack = findViewById(R.id.iv_back)
        tvMobileNumber = findViewById(R.id.tv_mobile_number)

         otp1 = findViewById(R.id.otp1)
         otp2 = findViewById(R.id.otp2)
         otp3 = findViewById(R.id.otp3)
         otp4 = findViewById(R.id.otp4)
         otp5 = findViewById(R.id.otp5)
         otp6 = findViewById(R.id.otp6)
        setupOtpInputs()
         //commingFromValue = intent.getStringExtra("KEY_COMMING_FROM")!!
        commingFromValue = intent.getStringExtra("KEY_COMMING_FROM") ?: ""
        mobileNumberValue = intent.getStringExtra("KEY_MOBILE_NUMBER") ?: ""
        tvMobileNumber.text = "A 6 digit code has been send to $mobileNumberValue"

        btnVerifyOtp.setOnClickListener {

           // val mobile = ccp.fullNumberWithPlus
            val otp = getOtp()

            if (otp.isEmpty()) {
                otp6.error = "Enter OTP"
                return@setOnClickListener
            }

          //  viewModel.verifyOtp(mobileNumberValue, otp)
        }

        /*lifecycleScope.launch {
            viewModel.otpVerifyState.collect { result ->

                when (result) {

                    is NetworkResult.Loading -> {
                        Log.d("API", "Loading")
                    }

                    is NetworkResult.Success -> {
                        Log.d("API", "Success: ${result.data}")

                        when (commingFromValue) {
                            "FORGOT_PASSWORD" -> {
                                startActivity(Intent(this@OtpVerificationActivity, CreatePassword::class.java))
                                finish()
                            }
                            "SIGN_IN_OTP" -> {
                                startActivity(Intent(this@OtpVerificationActivity, CreateAccount::class.java))
                                finish()
                            }
                            else -> finish()
                        }
                    }

                    is NetworkResult.Error -> {
                        Log.e("API", "Error: ${result.message}")

                        // ✅ SHOW ERROR TO USER
                        Toast.makeText(
                            this@OtpVerificationActivity,
                            result.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }*/



       /* btnSbmit.setOnClickListener {

            if(commingFromValue.equals("FORGOT_PASSWORD")){
                startActivity(Intent(this@OtpVerificationActivity, CreatePassword::class.java))
                finish()
            }else if(commingFromValue.equals("SIGN_IN_OTP")){
                startActivity(Intent(this@OtpVerificationActivity, CreateAccount::class.java))
                finish()
            }else{
                finish()
            }

        }*/

        ivBack.setOnClickListener {

            finish()
        }

       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }

    private fun setupOtpInputs() {

        moveNext(otp1, otp2)
        moveNext(otp2, otp3)
        moveNext(otp3, otp4)
        moveNext(otp4, otp5)
        moveNext(otp5, otp6)

        movePrevious(otp2, otp1)
        movePrevious(otp3, otp2)
        movePrevious(otp4, otp3)
        movePrevious(otp5, otp4)
        movePrevious(otp6, otp5)
    }

    private fun moveNext(current: EditText, next: EditText) {

        current.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (s?.length == 1) {
                    next.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun movePrevious(current: EditText, previous: EditText) {

        current.setOnKeyListener { _, keyCode, event ->

            if (keyCode == KeyEvent.KEYCODE_DEL && current.text.isEmpty()) {
                previous.requestFocus()
            }

            false
        }
    }

    // Get full OTP
    private fun getOtp(): String {

        return otp1.text.toString() +
                otp2.text.toString() +
                otp3.text.toString() +
                otp4.text.toString() +
                otp5.text.toString() +
                otp6.text.toString()
    }
}