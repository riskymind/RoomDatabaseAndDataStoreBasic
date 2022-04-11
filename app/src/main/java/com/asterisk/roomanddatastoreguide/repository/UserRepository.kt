package com.asterisk.roomanddatastoreguide.repository

import com.asterisk.roomanddatastoreguide.data.UserDao
import com.asterisk.roomanddatastoreguide.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
) {

    // Insert User into Database
    suspend fun createUserRecord(user: User): Long {
        return userDao.insert(user)
    }

    // Get single user
    fun getSingleUser(id: Long): Flow<User> = userDao.getSingleUser(id)

    // Get Users
    val getUsers get() = userDao.getUsers()

    // Delete Users
    suspend fun deleteUsers() = userDao.delete()

    // Delete single user
    suspend fun deleteSingleUser(id: Long) = userDao.deleteSingleUser(id)

}