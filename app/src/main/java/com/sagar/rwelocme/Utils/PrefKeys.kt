package com.sagar.rwelocme.Utils
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {

    val USER_TOKEN = stringPreferencesKey("token")
    val LOGIN_SESSION = booleanPreferencesKey("login_session")
    val USER_ID = stringPreferencesKey("id")
    val USER_EMAIL = stringPreferencesKey("email")
    val USER_MOBILE = stringPreferencesKey("mobile")
    val USER_NAME = stringPreferencesKey("firstName")
    val USER_LAST_NAME = stringPreferencesKey("lastName")
    val USER_DISPLAY_NAME = stringPreferencesKey("displayName")
    val USER_GENDER = stringPreferencesKey("gender")
    val USER_COUNTRY_ID = stringPreferencesKey("countryId")
    val USER_BIO = stringPreferencesKey("bio")
    val USER_ADDRESS = stringPreferencesKey("address")
    val USER_PROFILE_IMAGE = stringPreferencesKey("profileImage")

}

