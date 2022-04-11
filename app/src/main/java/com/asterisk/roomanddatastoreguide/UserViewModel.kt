package com.asterisk.roomanddatastoreguide

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asterisk.roomanddatastoreguide.models.User
import com.asterisk.roomanddatastoreguide.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {


    // insert user response
    private val _insertResponse = MutableLiveData<Long>()
    val insertResponse: LiveData<Long> = _insertResponse

    // Insert User to DB
    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.createUserRecord(user)
            _insertResponse.postValue(response)
        }
    }


}