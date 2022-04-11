package com.asterisk.roomanddatastoreguide.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.asterisk.roomanddatastoreguide.preferences.PreferenceStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatastoreViewModel @Inject constructor(
    private val preferenceStorage: PreferenceStorage,
) : ViewModel() {

    // Save key as Livedata
    val savedKey = preferenceStorage.saveKey().asLiveData()

    fun setSaveKey(key: Boolean) {
        viewModelScope.launch {
            preferenceStorage.setSaveKey(key)
        }
    }
}