package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.sagar.rwelocme.MainActivity
import com.sagar.rwelocme.R

class AsSignInActivity : AppCompatActivity() {

    private lateinit var tvForgetPassword: TextView
    private lateinit var tvSignInWithOtp: TextView
    private lateinit var btnSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false)
        setContentView(R.layout.activity_as_sign_in)
        hideSystemBars()

        tvForgetPassword = findViewById(R.id.tvForgot)
        tvSignInWithOtp = findViewById(R.id.tv_sign_in_with_otp)
        btnSignIn = findViewById(R.id.btn_sign_in)

        tvForgetPassword.setOnClickListener {
            // Handle Forget Password click
            startActivity(Intent(this@AsSignInActivity, ForgotPassword::class.java))

        }

        tvSignInWithOtp.setOnClickListener {
            // Handle Sign In with OTP click
            val intent = Intent(this@AsSignInActivity, SignInWithOtpActivity::class.java)
            intent.putExtra("KEY_MOBILE", "9876543210")
            intent.putExtra("KEY_FROM", "SIGN_IN")
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            val intent = Intent(this@AsSignInActivity, MainActivity::class.java)
            startActivity(intent)
        }


    }


    private fun hideSystemBars() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.apply {
                hide(
                    WindowInsets.Type.statusBars() or
                            WindowInsets.Type.navigationBars()
                )
                systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        View.SYSTEM_UI_FLAG_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

}