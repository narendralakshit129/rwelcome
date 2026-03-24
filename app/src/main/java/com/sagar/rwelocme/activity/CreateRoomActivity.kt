package com.sagar.rwelocme.activity

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sagar.rwelocme.R
import com.sagar.rwelocme.Utils.StorePref
import com.sagar.rwelocme.presentation.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class CreateRoomActivity : AppCompatActivity() {

    private lateinit var ivBack: ImageView
    private lateinit var ivProfile: ImageView
    private lateinit var btnCreateRoom: Button
    private lateinit var etRoomName: EditText
    private lateinit var etRoomDesc: EditText
    private lateinit var tvRoomType: TextView

    private val viewModel: RoomViewModel by viewModels()

    @Inject
    lateinit var pref: StorePref

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_room)

        initViews()
        setupClicks()
        observeState()
    }

    private fun initViews() {
        ivBack = findViewById(R.id.ivBack)
        ivProfile = findViewById(R.id.ivProfile)
        btnCreateRoom = findViewById(R.id.btn_create_room)
        etRoomName = findViewById(R.id.et_room_name)
        etRoomDesc = findViewById(R.id.et_room_bio)
        tvRoomType = findViewById(R.id.tv_room_type)
    }

    private fun setupClicks() {

        ivBack.setOnClickListener { finish() }

        // 📸 Image Picker
        ivProfile.setOnClickListener {
            galleryLauncher.launch("image/*")
        }

        // 🔽 Room Type Selection
        tvRoomType.setOnClickListener {
            val options = arrayOf("public", "private")

            AlertDialog.Builder(this)
                .setTitle("Select Room Type")
                .setItems(options) { _, which ->
                    tvRoomType.text = options[which]
                    viewModel.roomType.value = options[which]
                }
                .show()
        }

        btnCreateRoom.setOnClickListener {

            viewModel.roomName.value = etRoomName.text.toString()
            viewModel.roomDescription.value = etRoomDesc.text.toString()

            lifecycleScope.launch {
                val token = pref.getUserToken()
                viewModel.createRoom(token)
            }
        }
    }

    private fun observeState() {

        lifecycleScope.launch {

            // 🔄 Loading
            launch {
                viewModel.isLoading.collect {
                    if (it) {
                        Toast.makeText(
                            this@CreateRoomActivity,
                            "Creating room...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            // ❌ Error
            launch {
                viewModel.error.collect {
                    it?.let {
                        Toast.makeText(this@CreateRoomActivity, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }

            // ✅ Success
            launch {
                viewModel.isSuccess.collect { success ->
                    if (success) {

                        Toast.makeText(
                            this@CreateRoomActivity,
                            "Room Created Successfully 🎉",
                            Toast.LENGTH_SHORT
                        ).show()



                        viewModel.resetSuccess() // prevent repeat
                        finish()
                    }
                }
            }
        }
    }

    // ================= IMAGE PICKER =================

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

            uri?.let {
                imageUri = it
                ivProfile.setImageURI(it)

                val file = uriToFile(it)

                lifecycleScope.launch {
                    val token = pref.getUserToken()

                    // 🔥 Replace this with your upload API
                    // After upload success, set actual key
                    viewModel.roomImageKey = file.name
                }
            }
        }

    private fun uriToFile(uri: Uri): File {
        val file = File(cacheDir, "room_image.jpg")

        val inputStream = contentResolver.openInputStream(uri)
        val outputStream = file.outputStream()

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        return file
    }
}