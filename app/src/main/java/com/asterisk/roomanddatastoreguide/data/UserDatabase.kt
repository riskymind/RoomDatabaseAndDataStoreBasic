package com.asterisk.roomanddatastoreguide.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asterisk.roomanddatastoreguide.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}