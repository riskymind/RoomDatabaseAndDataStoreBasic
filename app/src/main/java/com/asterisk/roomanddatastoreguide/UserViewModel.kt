package com.asterisk.roomanddatastoreguide

import androidx.lifecycle.ViewModel
import com.asterisk.roomanddatastoreguide.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {



}