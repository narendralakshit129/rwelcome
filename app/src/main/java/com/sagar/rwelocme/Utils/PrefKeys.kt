package com.sagar.rwelocme.Utils
import androidx.datastore.preferences.core.stringPreferencesKey

object PrefKeys {
    val DEVICE_ID = stringPreferencesKey("device_id")
    val DEVICE_NAME = stringPreferencesKey("device_name")
    val APP_TYPE = stringPreferencesKey("app_type")
    val USER_TOKEN = stringPreferencesKey("token")
    val USER_ID = stringPreferencesKey("id")
    val USER_MOBILE = stringPreferencesKey("mobile")
    val USER_NAME = stringPreferencesKey("firstName")
}