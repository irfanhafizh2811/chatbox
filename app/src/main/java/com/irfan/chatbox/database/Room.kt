package com.irfan.chatbox.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface ChatDao {

    @Query("select * from DatabaseChat")
    fun getDatabaseChat(): LiveData<List<DatabaseChat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(chats: List<DatabaseChat>)

    @Query("select * from DatabaseMessage WHERE messageId LIKE :messageId")
    fun getMessages(messageId: String): LiveData<List<DatabaseMessage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessages(databaseMessages: List<DatabaseMessage>)
}

@Database(
    entities = [
        DatabaseChat::class,
        DatabaseMessage::class
    ],
    version = 1,
    exportSchema = false
)

abstract class ChatDatabase : RoomDatabase() {
    abstract val chatDao: ChatDao
}