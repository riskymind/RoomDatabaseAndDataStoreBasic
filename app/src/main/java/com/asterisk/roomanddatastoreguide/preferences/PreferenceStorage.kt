package com.asterisk.roomanddatastoreguide.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    // Check if User has saved some details to database, move to details screen if saved
    fun saveKey(): Flow<Boolean>
    suspend fun setSaveKey(order: Boolean)
}