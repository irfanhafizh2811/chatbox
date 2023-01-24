package com.irfan.chatbox.di

import android.content.Context
import androidx.room.Room
import com.irfan.chatbox.database.ChatDao
import com.irfan.chatbox.database.ChatDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppChatDatabase(@ApplicationContext appContext: Context): ChatDatabase {
        return Room.databaseBuilder(
            appContext,
            ChatDatabase::class.java,
            "Chats"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChatDao(chatDatabase: ChatDatabase): ChatDao {
        return chatDatabase.chatDao
    }
}