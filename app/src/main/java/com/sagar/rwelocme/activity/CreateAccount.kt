package com.sagar.rwelocme.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sagar.rwelocme.R

class CreateAccount : AppCompatActivity() {

    private lateinit var btnCreateProfile: Button
    private lateinit var ivBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)

        btnCreateProfile = findViewById(R.id.btn_create_profile)
        ivBack = findViewById(R.id.iv_back)

        btnCreateProfile.setOnClickListener {

            // startActivity(Intent(this@OtpVerificationActivity, CreatePassword::class.java))
            finish()

        }

        ivBack.setOnClickListener {

            // startActivity(Intent(this@ForgotPassword, AsSignInActivity::class.java))
            finish()
        }

       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}