package com.asterisk.roomanddatastoreguide.data

import androidx.room.*
import com.asterisk.roomanddatastoreguide.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // Insert into database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    // Get users
    @Query("SELECT * FROM users_table ORDER BY id DESC")
    fun getUsers(): Flow<List<User>>

    // Get single user
    @Query("SELECT * FROM users_table WHERE id = :id")
    fun getSingleUser(id: Long): Flow<User>


    // Update user
    @Update
    suspend fun update(user: User)

    // Delete single user
    @Query("DELETE FROM users_table WHERE id = :id")
    suspend fun deleteSingleUser(id: Long)

    // Delete all users
    @Query("DELETE FROM users_table")
    suspend fun delete()
}