package com.asterisk.roomanddatastoreguide

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asterisk.roomanddatastoreguide.models.User
import com.asterisk.roomanddatastoreguide.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {


    // Insert user response
    private val _insertResponse = MutableLiveData<Long>()
    val insertResponse: LiveData<Long> = _insertResponse

    // Insert User to DB
    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.createUserRecord(user)
            _insertResponse.postValue(response)
        }
    }

    // Query users response
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUsers
                .catch { e ->
                    Log.e("Error", "${e.message}")
                }
                .collect {
                    _users.value = it
                }
        }
    }


}