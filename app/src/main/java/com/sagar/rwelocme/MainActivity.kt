package com.sagar.rwelocme

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.sagar.rwelocme.fragment.AsChatFragment
import com.sagar.rwelocme.fragment.AsHomeFragment
import com.sagar.rwelocme.fragment.AsMeProfileFragment
import com.sagar.rwelocme.fragment.AsPostFragment
import com.sagar.rwelocme.fragment.AsRoomFragment

class MainActivity : AppCompatActivity() {

    lateinit var iconHome: ImageView
    lateinit var textHome: TextView
    lateinit var iconPost: ImageView
    lateinit var textPost: TextView
    lateinit var iconRoom: ImageView
    lateinit  var textRoom: TextView
    lateinit var iconChat: ImageView
    lateinit  var textChat: TextView
    lateinit var iconMe: ImageView
    lateinit  var textMe: TextView
    lateinit  var navHome: LinearLayout
    lateinit  var navPost: LinearLayout
    lateinit  var navChat: LinearLayout
    lateinit  var navRoom: LinearLayout
    lateinit  var navMe: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false)
        setContentView(R.layout.activity_main)

        hideSystemBars()
        // Load Home by default
        if (savedInstanceState == null) {
            loadFragment(AsHomeFragment())
        }

        iconHome = findViewById(R.id.ic_home)
        textHome = findViewById(R.id.txt_home)
        iconPost = findViewById(R.id.ic_post)
        textPost = findViewById(R.id.txt_post)
        iconRoom = findViewById(R.id.ic_room)
        textRoom = findViewById(R.id.txt_room)
        iconChat = findViewById(R.id.ic_chat)
        textChat = findViewById(R.id.txt_chat)
        iconMe = findViewById(R.id.ic_me)
        textMe = findViewById(R.id.txt_me)

        navHome = findViewById(R.id.navHome)
        navPost = findViewById(R.id.navPost)
        navChat = findViewById(R.id.navChat)
        navRoom = findViewById(R.id.navRoom)
        navMe = findViewById(R.id.navMe)

        setupBottomNav()
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }



    private fun setupBottomNav() {

        fun resetAll() {
            // Unselect all icons/text
            iconHome.setColorFilter(ContextCompat.getColor(this, R.color.black))
            textHome.setTextColor(ContextCompat.getColor(this, R.color.black))

            iconPost.setColorFilter(ContextCompat.getColor(this, R.color.black))
            textPost.setTextColor(ContextCompat.getColor(this, R.color.black))

            iconRoom.setColorFilter(ContextCompat.getColor(this, R.color.black))
            textRoom.setTextColor(ContextCompat.getColor(this, R.color.black))

            iconChat.setColorFilter(ContextCompat.getColor(this, R.color.black))
            textChat.setTextColor(ContextCompat.getColor(this, R.color.black))

            iconMe.setColorFilter(ContextCompat.getColor(this, R.color.black))
            textMe.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        fun selectTab(icon: ImageView, text: TextView) {
            resetAll()

            icon.setColorFilter(ContextCompat.getColor(this, R.color.blue))
            text.setTextColor(ContextCompat.getColor(this, R.color.blue))
        }

        // Default selected tab
        selectTab(iconHome, textHome)

        // Click listeners
        navHome.setOnClickListener {
            selectTab(iconHome, textHome)
            loadFragment(AsHomeFragment())
        }

        navPost.setOnClickListener {
            selectTab(iconPost, textPost)
            loadFragment(AsPostFragment())
        }

        navRoom.setOnClickListener {
            selectTab(iconRoom, textRoom)
            loadFragment(AsRoomFragment())
        }

        navChat.setOnClickListener {
            selectTab(iconChat, textChat)
               loadFragment(AsChatFragment())
        }

        navMe.setOnClickListener {
            selectTab(iconMe, textMe)
               loadFragment(AsMeProfileFragment())
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