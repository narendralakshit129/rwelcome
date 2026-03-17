package com.sagar.rwelocme.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sagar.rwelocme.R
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.presentation.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateAccount : AppCompatActivity() {

    private lateinit var btnCreateProfile: Button
    private lateinit var ivBack: ImageView
    private lateinit var ivProfile: ImageView
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)

        viewModel.getCountries()

        btnCreateProfile = findViewById(R.id.btn_create_profile)
        ivBack = findViewById(R.id.iv_back)
        ivProfile = findViewById(R.id.ivProfile)

        btnCreateProfile.setOnClickListener {

            // startActivity(Intent(this@OtpVerificationActivity, CreatePassword::class.java))
            finish()

        }

        ivBack.setOnClickListener {

            // startActivity(Intent(this@ForgotPassword, AsSignInActivity::class.java))
            finish()
        }


        viewModel.countries.observe(this) {

            when (it) {

                is NetworkResult.Success -> {
                    val list = it.data
                    list?.forEach {
                        Log.d("COUNTRY", it.name)
                    }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    // show loader
                }
            }
        }


       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}