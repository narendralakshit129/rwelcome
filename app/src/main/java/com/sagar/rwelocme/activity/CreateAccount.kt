package com.sagar.rwelocme.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sagar.rwelocme.MainActivity
import com.sagar.rwelocme.R
import com.sagar.rwelocme.Utils.StorePref
import com.sagar.rwelocme.callback.OnOptionClickListener
import com.sagar.rwelocme.comman.Gender
import com.sagar.rwelocme.di.NetworkResult
import com.sagar.rwelocme.dialog.GalleryCameraBottomSheet
import com.sagar.rwelocme.domain.model.Country
import com.sagar.rwelocme.presentation.ui.state.CreateAccountUiState
import com.sagar.rwelocme.presentation.viewmodel.UpdateProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class CreateAccount : AppCompatActivity() , OnOptionClickListener {

    private lateinit var ivProfile: ImageView
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var tvNationality: TextView
    private lateinit var llMale: LinearLayout
    private lateinit var llFemale: LinearLayout
    private lateinit var tvMale: TextView
    private lateinit var tvFemale: TextView
    private lateinit var ivMale: ImageView
    private lateinit var ivFemale: ImageView
    private lateinit var etYourBio: EditText

    private lateinit var btnCreateProfile: Button
    private lateinit var ivBack: ImageView

    private val viewModel: UpdateProfileViewModel by viewModels()
    private var imageUri: Uri? = null
    private var imageKey: String = ""

    private var currentState: CreateAccountUiState? = null

    @Inject
    lateinit var pref: StorePref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_account)

        initViews()
        setupClicks()
        observeState()

        viewModel.getCountries()

    }

    private fun initViews() {

        ivBack = findViewById(R.id.iv_back)

        ivProfile = findViewById(R.id.ivProfile)
        etFirstName = findViewById(R.id.et_full_name)
        etLastName = findViewById(R.id.et_last_name)
        etYourBio = findViewById(R.id.et_tilBio)
        tvNationality = findViewById(R.id.tvNationality)

        llMale = findViewById(R.id.llMale)
        llFemale = findViewById(R.id.llFemale)

        ivMale = llMale.getChildAt(0) as ImageView
        tvMale = llMale.getChildAt(1) as TextView

        ivFemale = llFemale.getChildAt(0) as ImageView
        tvFemale = llFemale.getChildAt(1) as TextView

        btnCreateProfile = findViewById(R.id.btn_create_profile)

    }

    private fun setupClicks() {

        ivBack.setOnClickListener {
            finish()
        }

        ivProfile.setOnClickListener {
            val sheet = GalleryCameraBottomSheet()
            sheet.listener = this
            sheet.show(supportFragmentManager, "sheet")
        }

        llMale.setOnClickListener {
            viewModel.selectGender(Gender.male)
        }

        llFemale.setOnClickListener {
            viewModel.selectGender(Gender.female)
        }

        tvNationality.setOnClickListener {
            showCountryDialog(viewModel.uiState.value.countries)
        }

        btnCreateProfile.setOnClickListener {


            val state = currentState ?: return@setOnClickListener
            lifecycleScope.launch {
                val token = pref.getUserToken()   // get token value
                Log.d("API", "token: ${token}")
                val countryId = state.selectedCountryId
                val countryName = state.selectedCountry
                val code = state.selectedCountryCode
                val gender = state.selectedGender


                var profileImage = imageKey
                var firstName = etFirstName.text.toString()
                var lastName = etLastName.text.toString()
                var address = ""
                var displayName = firstName + " " + lastName
                var bio = etYourBio.text.toString()

                viewModel.createProfile(
                    profileImage,
                    firstName,
                    lastName,
                    address,
                    displayName,
                    gender,
                    countryId,
                    bio,
                    token
                )

            }


            // startActivity(Intent(this@OtpVerificationActivity, CreatePassword::class.java))
       //     startActivity(Intent(this@CreateAccount, MainActivity::class.java))
      //      finish()

        }
    }

    private fun observeState() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    // UI update
                    // Country
                    tvNationality.text =
                        if (state.selectedCountry.isEmpty())
                            "Select nationality"
                        else state.selectedCountry

                    // Gender UI
                    updateGenderUI(state.selectedGender)

                    // Image
                    state.imageUri?.let {
                        ivProfile.setImageURI(it)
                        // var file =  uriToFile(this@CreateAccount,it)
                        // viewModel.uploadImage(file)
                    }

                    // Error
                    state.error?.let {
                        Toast.makeText(this@CreateAccount, it, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uploadState.collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> {
                            Toast.makeText(this@CreateAccount, "Uploading...", Toast.LENGTH_SHORT).show()
                        }

                        is NetworkResult.Success -> {
                            Toast.makeText(this@CreateAccount, "Upload Successful", Toast.LENGTH_SHORT).show()
                            val data = result.data
                            imageKey = data?.key ?: ""

                            println("Uploaded image url: ${data?.url}")
                            println("Uploaded image url: ${data?.key}")
                            // Navigate to next screen
                        //    startActivity(Intent(this@CreateAccount, MainActivity::class.java))
                         //   finish()
                        }

                        is NetworkResult.Error -> {
                            Toast.makeText(
                                this@CreateAccount,
                                result.message ?: "Upload Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {}
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    currentState = state
                }
            }
        }


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.uploadProfileState.collect { result ->
                    when (result) {
                        is NetworkResult.Loading -> {
                            Toast.makeText(this@CreateAccount, "Uploading...", Toast.LENGTH_SHORT).show()
                        }

                        is NetworkResult.Success -> {
                            Toast.makeText(this@CreateAccount, "Upload Successful", Toast.LENGTH_SHORT).show()
                            val response = result.data
                            lifecycleScope.launch {

                                pref.setUserId(response.id.toString())
                                pref.setUserEmail(response.email ?: "")
                                pref.setUserMobile(response.mobile ?: "")
                                pref.setUserName(response.firstName ?: "")
                                pref.setUserLastName(response.lastName ?: "")
                                pref.setUserDisplayName(response.displayName ?: "")
                                pref.setUserGender(response.gender ?: "")
                                pref.setUserCountryId(""+response.countryId ?: "")
                                pref.setUserBio(response.bio ?: "")
                                pref.setUserAddress(response.address ?: "")
                                pref.setUserProfileImage(response.profileImage ?: "")
                                pref.setLoginStatus(true)
                                startActivity(Intent(this@CreateAccount, MainActivity::class.java))
                                finish()
                            }
                        }

                        is NetworkResult.Error -> {
                            Toast.makeText(
                                this@CreateAccount,
                                result.message ?: "Upload Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {}
                    }
                }
            }
        }

    }


    private fun updateGenderUI(gender: Gender?) {

        val selectedColor = Color.parseColor("#00B0FF")
        val defaultColor = Color.BLACK

        val isMale = gender == Gender.male

        llMale.setBackgroundResource(
            if (isMale) R.drawable.bg_gender_selected else R.drawable.bg_gender_card
        )

        llFemale.setBackgroundResource(
            if (gender == Gender.female) R.drawable.bg_gender_selected else R.drawable.bg_gender_card
        )

        tvMale.setTextColor(if (isMale) selectedColor else defaultColor)
        tvFemale.setTextColor(if (gender == Gender.female) selectedColor else defaultColor)

        ivMale.setColorFilter(if (isMale) selectedColor else defaultColor)
        ivFemale.setColorFilter(if (gender == Gender.female) selectedColor else defaultColor)
    }

    private fun showCountryDialog(countries: List<Country>) {

        val list = listOf("Select nationality") + countries.map {
            "${it.name} (${it.code})"
        }

        AlertDialog.Builder(this)
            .setTitle("Select Nationality")
            .setItems(list.toTypedArray()) { _, which ->
                if (which != 0) {
                    val selectedCountry = countries[which - 1]
                    viewModel.selectCountry(
                        selectedCountry.id,
                        selectedCountry.name,
                        selectedCountry.code   // 👈 passing code
                    )
                }
            }
            .show()
    }

    // 🖼️ Gallery result
    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->

            uri?.let {
                ivProfile.setImageURI(it)
                val file = uriToFile(this, it)
                lifecycleScope.launch {
                    val token = pref.getUserToken()   // get token value
                    Log.d("API", "token: ${token}")
                    viewModel.uploadImage(file, token)
                }
            }
        }

    // 📸 Camera result
    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && imageUri != null) {
                viewModel.setImage(imageUri!!)
                val file = uriToFile(this, imageUri!!)
                lifecycleScope.launch {
                    val token = pref.getUserToken()   // get token value
                    Log.d("API", "token: ${token}")
                    viewModel.uploadImage(file, token)
                }
            }
        }



    // 🔐 Camera permission
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) openCamera()
            else Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }


    override fun onCameraClick() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED
        ) {
            openCamera()
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    override fun onGalleryClick() {
        galleryLauncher.launch("image/*")
    }

    private fun openCamera() {
        imageUri = createImageUri()
        cameraLauncher.launch(imageUri!!)
    }


    private fun createImageUri(): Uri {
        val file = File(cacheDir, "camera.jpg")
        return FileProvider.getUriForFile(
            this,
            "$packageName.provider",
            file
        )
    }

    fun uriToFile(context: Context, uri: Uri): File {
        val contentResolver = context.contentResolver

        val fileName = getFileName(context, uri) ?: "temp_file"
        val file = File(context.cacheDir, fileName)

        val inputStream = contentResolver.openInputStream(uri)
        val outputStream = file.outputStream()

        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        return file
    }

    fun getFileName(context: Context, uri: Uri): String? {
        var name: String? = null

        val cursor = context.contentResolver.query(uri, null, null, null, null)

        cursor?.use {
            if (it.moveToFirst()) {
                val index = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                if (index != -1) {
                    name = it.getString(index)
                }
            }
        }

        return name
    }


}