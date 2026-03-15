package com.sagar.rwelocme.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.adapter.CoupleRankAdapter
import com.sagar.rwelocme.adapter.HomeUserAdapter
import com.sagar.rwelocme.model.CoupleRankModel
import com.sagar.rwelocme.model.HomeUserModel

class CoupleRankingActivity : AppCompatActivity() {

    private lateinit var ivBackRoom: ImageView

    private lateinit var recyclerView: RecyclerView
    private lateinit var coupleRankAdapter: CoupleRankAdapter
    private val coupleRankList = ArrayList<CoupleRankModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_couple_ranking)
        hideSystemBars()
        ivBackRoom = findViewById(R.id.ivBack)
        ivBackRoom.setOnClickListener {
            finish()
        }


        recyclerView = findViewById(R.id.recycler_couple)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        loadDummyData()


        coupleRankAdapter = CoupleRankAdapter(coupleRankList)
        recyclerView.adapter = coupleRankAdapter

    }


    private fun loadDummyData() {
        coupleRankList.clear()

        coupleRankList.add(CoupleRankModel(1,"Rakesh", "https://i.pravatar.cc/150?img=1", "Kriti",  "https://i.pravatar.cc/150?img=5","3.2B"))

        coupleRankList.add(  CoupleRankModel(2,"Yogi", "https://i.pravatar.cc/150?img=2", "Nayanthara", "https://i.pravatar.cc/150?img=16","1.0B"))

        coupleRankList.add(  CoupleRankModel(3,"Azhar ", "https://i.pravatar.cc/150?img=3", "Rashmika", "https://i.pravatar.cc/150?img=19","2.2B"))

        coupleRankList.add( CoupleRankModel(4,"Navin", "https://i.pravatar.cc/150?img=4", "Hania",  "https://i.pravatar.cc/150?img=20","1.2B"))

        coupleRankList.add( CoupleRankModel(5,"ZEHER", "https://i.pravatar.cc/150?img=6", "Mahira", "https://i.pravatar.cc/150?img=21", "1.1B"))

        coupleRankList.add( CoupleRankModel(6,"Arjun", "https://i.pravatar.cc/150?img=7", "Saba",  "https://i.pravatar.cc/150?img=23","1.7B"))

        coupleRankList.add( CoupleRankModel(7,"Pankaj", "https://i.pravatar.cc/150?img=8", "Ayeza",  "https://i.pravatar.cc/150?img=24","1B"))

        coupleRankList.add( CoupleRankModel(8,"Amit", "https://i.pravatar.cc/150?img=12", "Mehwish",  "https://i.pravatar.cc/150?img=25","1.3B"))

        coupleRankList.add( CoupleRankModel(9,"Narendra", "https://i.pravatar.cc/150?img=14", "Iqra",  "https://i.pravatar.cc/150?img=26","1.8B"))

        coupleRankList.add( CoupleRankModel(10,"Sagar ", "https://i.pravatar.cc/150?img=11", "Kajal",  "https://i.pravatar.cc/150?img=27","1.9B"))


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