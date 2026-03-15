package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sagar.rwelocme.R

class SignInWithOtpActivity : AppCompatActivity() {

    private lateinit var btnSendOtp: Button
    private lateinit var tvHavePassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in_with_otp)


        btnSendOtp = findViewById(R.id.btn_send_otp)
        tvHavePassword = findViewById(R.id.txt_have_password)

        btnSendOtp.setOnClickListener {

            val intent = Intent(this@SignInWithOtpActivity, OtpVerificationActivity::class.java)
            intent.putExtra("KEY_COMMING_FROM", "SIGN_IN_OTP")
            startActivity(intent)
            finish()

        }

        tvHavePassword.setOnClickListener {
            finish()
        }


    }
}