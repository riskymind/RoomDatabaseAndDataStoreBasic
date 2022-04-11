package com.asterisk.roomanddatastoreguide.di

import com.asterisk.roomanddatastoreguide.preferences.PreferenceImpl
import com.asterisk.roomanddatastoreguide.preferences.PreferenceStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindsPreferenceStorage(preferenceImpl: PreferenceImpl): PreferenceStorage
}