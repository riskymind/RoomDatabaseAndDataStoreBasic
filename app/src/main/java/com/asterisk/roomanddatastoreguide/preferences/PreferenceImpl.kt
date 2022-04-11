package com.asterisk.roomanddatastoreguide.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.asterisk.roomanddatastoreguide.other.datastore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceImpl @Inject constructor(
    @ApplicationContext context: Context,
) : PreferenceStorage {

    private val dataStore = context.datastore

    // Preference Keys
    private object PreferenceKeys {
        val SAVED_KEY = booleanPreferencesKey("saved_key")
    }

    override fun saveKey() = dataStore.data.catch {
        if (it is IOException) {
            emit(emptyPreferences())
        } else {
            throw it
        }
    }.map {
        it[PreferenceKeys.SAVED_KEY] ?: false
    }


    override suspend fun setSaveKey(order: Boolean) {
        dataStore.edit {
            it[PreferenceKeys.SAVED_KEY] = order
        }
    }
}