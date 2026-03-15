package com.sagar.rwelocme.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.adapter.UserRankAdapter
import com.sagar.rwelocme.model.UserRankModel


class UserRankingActivity : AppCompatActivity() {

    private lateinit var ivBackRoom: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var userRankAdapter: UserRankAdapter
    private val userRankList = ArrayList<UserRankModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_ranking)
        hideSystemBars()
        ivBackRoom = findViewById(R.id.ivBack)
        ivBackRoom.setOnClickListener {
            finish()
        }

        recyclerView = findViewById(R.id.recycler_ranking)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        loadDummyData()


        userRankAdapter = UserRankAdapter(userRankList)
        recyclerView.adapter = userRankAdapter
    }


    private fun loadDummyData() {
        userRankList.clear()

        userRankList.add(UserRankModel(1,"Rakesh", "https://i.pravatar.cc/150?img=1", "3.2B"))

        userRankList.add(  UserRankModel(2,"Narendra", "https://i.pravatar.cc/150?img=2", "1.0B"))

        userRankList.add(  UserRankModel(3, "Rashmika", "https://i.pravatar.cc/150?img=19","2.2B"))

        userRankList.add( UserRankModel(4,"Navin", "https://i.pravatar.cc/150?img=4", "1.2B"))

        userRankList.add( UserRankModel(5,  "Mahira", "https://i.pravatar.cc/150?img=21", "1.1B"))
        
        userRankList.add( UserRankModel(6,"Arjun", "https://i.pravatar.cc/150?img=7", "1.7B"))

        userRankList.add( UserRankModel(7, "Ayeza",  "https://i.pravatar.cc/150?img=24","1B"))

        userRankList.add( UserRankModel(8, "Mehwish",  "https://i.pravatar.cc/150?img=25","1.3B"))

        userRankList.add( UserRankModel(9,"Yogi", "https://i.pravatar.cc/150?img=14", "1.8B"))

        userRankList.add( UserRankModel(10, "Kajal",  "https://i.pravatar.cc/150?img=27","1.9B"))

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