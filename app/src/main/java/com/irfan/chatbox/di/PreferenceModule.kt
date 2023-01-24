package com.irfan.chatbox.di

import android.content.Context
import android.content.SharedPreferences
import com.irfan.chatbox.pref.ApplicationPref
import com.irfan.chatbox.pref.CorePref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    const val CORE_PREF = "CORE_PREF"

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext appContext: Context) =
        appContext.getSharedPreferences(CORE_PREF, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideCorePref(sharedPreferences: SharedPreferences) =
        CorePref(sharedPreferences)

    @Singleton
    @Provides
    fun provideApplicationPref(corePref: CorePref) = ApplicationPref(corePref)
}