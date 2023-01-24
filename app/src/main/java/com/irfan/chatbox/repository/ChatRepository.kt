package com.irfan.chatbox.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.irfan.chatbox.database.ChatDatabase
import com.irfan.chatbox.database.asDomainModel
import com.irfan.chatbox.domain.Chat
import com.irfan.chatbox.domain.Response
import com.irfan.chatbox.domain.asDatabaseModel
import com.irfan.chatbox.pref.ApplicationPref
import com.irfan.chatbox.util.readJsonAssetToString
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class ChatRepository @Inject constructor(
    @ApplicationContext val context: Context,
    val database: ChatDatabase,
    val applicationPref: ApplicationPref
) {

    val chats: LiveData<MutableList<Chat>> =
        Transformations.map(database.chatDao.getDatabaseChat()) {
            it.asDomainModel()
        }

    suspend fun refreshChats() {
        try {
            val chatsText = context.readJsonAssetToString("chats.json")
            val jsonAdapter = Moshi.Builder().build().adapter(Response::class.java)
            jsonAdapter.fromJson(chatsText)?.let { response ->
                val chatResponse = response.chatResponse
                database.chatDao.insertAll(chatResponse.inbox.asDatabaseModel())
                response.chatResponse.inbox.forEach {
                    val userId = chatResponse.userDetail.userId
                    applicationPref.userId = userId
                    val messages = it.messages.asDatabaseModel(userId.plus(it.senderId))
                    database.chatDao.insertMessages(messages)
                }
            }
        } catch (e: Exception) {
            Timber.w(e)
        }
    }
}