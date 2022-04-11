package com.asterisk.roomanddatastoreguide.di

import android.content.Context
import androidx.room.Room
import com.asterisk.roomanddatastoreguide.data.UserDao
import com.asterisk.roomanddatastoreguide.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserDatabase(
        @ApplicationContext context: Context,
    ): UserDatabase =
        Room.databaseBuilder(context, UserDatabase::class.java, "user_db")
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun provideUserDao(db: UserDatabase): UserDao =
        db.getUserDao()



}