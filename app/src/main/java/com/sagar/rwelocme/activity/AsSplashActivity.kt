package com.sagar.rwelocme.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.sagar.rwelocme.MainActivity
import com.sagar.rwelocme.R
import com.sagar.rwelocme.Utils.StorePref
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class AsSplashActivity : AppCompatActivity() {

    private var keepSplash  = false
    @Inject
    lateinit var storePref: StorePref
    override fun onCreate(savedInstanceState: Bundle?) {

        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition { keepSplash }

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false)

        setContentView(R.layout.activity_as_splash)
        hideSystemBars()
        lifecycleScope.launch {
            delay(2000)
            keepSplash = false
            val isLoggedIn = storePref.isLoginStatus() // ✅ call suspend function
            if (isLoggedIn) {
                startActivity(Intent(this@AsSplashActivity, MainActivity::class.java))
                finish()
            }else {
                startActivity(Intent(this@AsSplashActivity, AsSignInActivity::class.java))
                finish()
            }
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