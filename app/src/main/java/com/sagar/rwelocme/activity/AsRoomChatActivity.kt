package com.sagar.rwelocme.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sagar.rwelocme.R
import com.sagar.rwelocme.adapter.RoomChairAdapter
import com.sagar.rwelocme.model.ChairModel
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView

import androidx.core.view.WindowCompat

class AsRoomChatActivity : AppCompatActivity() {

    private lateinit var ivClose: ImageView
    private lateinit var recyclerView: RecyclerView
    private lateinit var roomChatAdapter: RoomChairAdapter
    private val chairList = ArrayList<ChairModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_as_room_chat)
        enableFullScreen()
        ivClose = findViewById(R.id.ivClose)
        recyclerView = findViewById(R.id.recyclerSeats)
        val layoutManager = GridLayoutManager(
            this,
            4, // number of rows
            RecyclerView.VERTICAL,
            false
        )
        recyclerView.layoutManager=layoutManager
        recyclerView.setHasFixedSize(true)
        loadDummyData()


        roomChatAdapter = RoomChairAdapter(chairList) { room ->
            Toast.makeText(this, "Clicked: ${room.chairNum}", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = roomChatAdapter

        ivClose.setOnClickListener {
            finish()
        }
    }

    private fun loadDummyData() {
        chairList.clear()

        chairList.add(ChairModel("1", "Lv. 82"))

        chairList.add(ChairModel("1", "Lv. 44"))

        chairList.add(ChairModel("1 ", "Lv. 30"))

        chairList.add(ChairModel("4", "Lv. 10"))

        chairList.add(ChairModel("5", "Lv. 12"))

        chairList.add(ChairModel("6", "Lv. 12"))

        chairList.add(ChairModel("7", "Lv. 14"))

        chairList.add(ChairModel("8", "Lv. 16"))


    }

    private fun enableFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11+
            WindowCompat.setDecorFitsSystemWindows(window, false)

            window.insetsController?.apply {
                hide(
                    WindowInsets.Type.statusBars() or
                            WindowInsets.Type.navigationBars()
                )
                systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            // Android 10 and below
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