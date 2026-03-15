package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sagar.rwelocme.R

class AsSettingActivity : AppCompatActivity() {

    private lateinit var layoutChangePassword: LinearLayout
    private lateinit var layoutBlockList: LinearLayout
    private lateinit var layoutAccountBinding: LinearLayout
    private lateinit var layoutJoinAnAgency: LinearLayout
    private lateinit var layoutCustomerService: LinearLayout
    private lateinit var layoutFeedBack: LinearLayout
    private lateinit var tvSignInWithOtp: TextView
    private lateinit var btnSignIn: Button
    private lateinit var ivBack: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_as_setting)
        hideSystemBars()

        layoutChangePassword = findViewById<LinearLayout>(R.id.layout_change_password)
        layoutBlockList = findViewById<LinearLayout>(R.id.layout_block_list)
        layoutAccountBinding = findViewById<LinearLayout>(R.id.layout_account_binding)
        layoutJoinAnAgency = findViewById<LinearLayout>(R.id.layout_join_an_agency)
        layoutCustomerService = findViewById<LinearLayout>(R.id.layout_customer_service)
        layoutFeedBack = findViewById<LinearLayout>(R.id.layout_feedback)
        ivBack = findViewById(R.id.ivBack)
        ivBack.setOnClickListener {
            finish()
        }

        layoutChangePassword.setOnClickListener {
            val intent = Intent(this, AsChangePasswordActivity::class.java)
            startActivity(intent)
        }


        layoutBlockList.setOnClickListener {
            val intent = Intent(this, AsBlockListActivity::class.java)
            startActivity(intent)
        }

        layoutAccountBinding.setOnClickListener {
            val intent = Intent(this, AsAccountBindingActivity::class.java)
            startActivity(intent)
        }

        layoutCustomerService.setOnClickListener {
            val intent = Intent(this, AsCustomerService::class.java)
            startActivity(intent)
        }
        layoutJoinAnAgency.setOnClickListener {
            val intent = Intent(this, JoinAnAgencyActivity::class.java)
            startActivity(intent)
        }
        layoutFeedBack.setOnClickListener {
            val intent = Intent(this, AsFeedbackActivity::class.java)
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