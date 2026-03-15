package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sagar.rwelocme.R

class OtpVerificationActivity : AppCompatActivity() {

    private lateinit var btnSbmit: Button
    private lateinit var ivBack: ImageView

    var commingFromValue: String ="";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otp_verification)

        btnSbmit = findViewById(R.id.btn_submit_otp)
        ivBack = findViewById(R.id.iv_back)

         //commingFromValue = intent.getStringExtra("KEY_COMMING_FROM")!!
         commingFromValue = intent.getStringExtra("KEY_COMMING_FROM") ?: ""



        btnSbmit.setOnClickListener {

            if(commingFromValue.equals("FORGOT_PASSWORD")){
                startActivity(Intent(this@OtpVerificationActivity, CreatePassword::class.java))
                finish()
            }else if(commingFromValue.equals("SIGN_IN_OTP")){
                startActivity(Intent(this@OtpVerificationActivity, CreateAccount::class.java))
                finish()
            }else{
                finish()
            }



        }

        ivBack.setOnClickListener {

            finish()
        }

       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}