package com.sagar.rwelocme.Utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StorePref @Inject constructor(@ApplicationContext private val context: Context) {

    // Save
    suspend fun setLoginStatus(isLogin: Boolean) {
        context.dataStore.edit {
            it[PrefKeys.LOGIN_SESSION] = isLogin
        }
    }

    suspend fun setUserToken(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_TOKEN] = value
        }
    }

    suspend fun setUserId(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_ID] = value
        }
    }

    suspend fun setUserEmail(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_EMAIL] = value
        }
    }

    suspend fun setUserMobile(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_MOBILE] = value
        }
    }

    suspend fun setUserName(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_NAME] = value
        }
    }

    suspend fun setUserLastName(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_LAST_NAME] = value
        }
    }

    suspend fun setUserDisplayName(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_DISPLAY_NAME] = value
        }
    }

    suspend fun setUserGender(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_GENDER] = value
        }
    }

    suspend fun setUserCountryId(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_COUNTRY_ID] = value
        }
    }

    suspend fun setUserBio(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_BIO] = value
        }
    }

    suspend fun setUserAddress(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_ADDRESS] = value
        }
    }

    suspend fun setUserProfileImage(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_PROFILE_IMAGE] = value
        }
    }



    // Get (Flow)

    suspend fun getUserToken(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_TOKEN] ?: "" }.first()
    }

    suspend fun isLoginStatus(): Boolean {
        return context.dataStore.data.map { it[PrefKeys.LOGIN_SESSION] ?: false }.first()
    }

    suspend fun getUserId(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_ID] ?: "" }.first()
    }

    suspend fun getUserEmail(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_EMAIL] ?: "" }.first()
    }
    suspend fun getUserMobile(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_MOBILE] ?: "" }.first()
    }

    suspend fun getUserName(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_NAME] ?: "" }.first()
    }

    suspend fun getUserLastName(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_LAST_NAME] ?: "" }.first()
    }

    suspend fun getUserDisplayName(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_DISPLAY_NAME] ?: "" }.first()
    }

    suspend fun getUserGender(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_GENDER] ?: "" }.first()
    }

    suspend fun getUserCountryId(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_COUNTRY_ID] ?: "" }.first()
    }

    suspend fun getUserBio(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_BIO] ?: "" }.first()
    }

    suspend fun getUserAddress(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_ADDRESS] ?: "" }.first()
    }

    suspend fun getUserProfileImage(): String {
        return context.dataStore.data.map { it[PrefKeys.USER_PROFILE_IMAGE] ?: "" }.first()
    }

}