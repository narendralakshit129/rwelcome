package com.sagar.rwelocme.Utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StorePref @Inject constructor(@ApplicationContext private val context: Context) {

    // Save
    suspend fun saveDeviceId(id: String) {
        context.dataStore.edit {
            it[PrefKeys.DEVICE_ID] = id
        }
    }

    suspend fun setDeviceName(value: String) {
        context.dataStore.edit {
            it[PrefKeys.DEVICE_NAME] = value
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
    suspend fun setUserName(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_NAME] = value
        }
    }
    suspend fun setUserMobile(value: String) {
        context.dataStore.edit {
            it[PrefKeys.USER_MOBILE] = value
        }
    }

    // Get (Flow)
    val getDeviceId: Flow<String> = context.dataStore.data
        .map { it[PrefKeys.DEVICE_ID] ?: "" }

    val getDeviceName: Flow<String> = context.dataStore.data
        .map { it[PrefKeys.DEVICE_NAME] ?: "" }

    val  getUserToken: Flow<String> = context.dataStore.data
        .map { it[PrefKeys.USER_TOKEN] ?: "" }

    val  getUserId: Flow<String> = context.dataStore.data
        .map { it[PrefKeys.USER_ID] ?: "" }

    val  getUserName: Flow<String> = context.dataStore.data
        .map { it[PrefKeys.USER_NAME] ?: "" }

    val  getUserMobile: Flow<String> = context.dataStore.data
        .map { it[PrefKeys.USER_MOBILE] ?: "" }


}