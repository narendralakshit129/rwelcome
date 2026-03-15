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

class ForgotPassword : AppCompatActivity() {

    private lateinit var btnNext: Button
    private lateinit var tvHavePassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forgot_password)


        btnNext = findViewById(R.id.btn_next)
        tvHavePassword = findViewById(R.id.txt_have_password)

        btnNext.setOnClickListener {

            val intent = Intent(this@ForgotPassword, OtpVerificationActivity::class.java)
            intent.putExtra("KEY_COMMING_FROM", "FORGOT_PASSWORD")
            startActivity(intent)
            finish()

        }

        tvHavePassword.setOnClickListener {
            finish()
        }



       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}