package com.irfan.chatbox.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.irfan.chatbox.database.ChatDatabase
import com.irfan.chatbox.database.asDomainModel
import com.irfan.chatbox.domain.Message
import javax.inject.Inject

class MessageRepository @Inject constructor(
    val database: ChatDatabase
) {

    fun getMessages(messageId: String): LiveData<MutableList<Message>> =
        Transformations.map(database.chatDao.getMessages(messageId)) {
            it.asDomainModel()
        }

}