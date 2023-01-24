package com.irfan.chatbox.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.irfan.chatbox.domain.Chat


@Entity
data class DatabaseChat constructor(
    @PrimaryKey
    val senderId: String,
    val senderName: String,
    val lastMessage: String,
    val lastMessageTime: String
)

fun List<DatabaseChat>.asDomainModel(): MutableList<Chat> {
    return map {
        Chat(
            senderId = it.senderId,
            senderName = it.senderName,
        ).apply {
            lastMessage = it.lastMessage
            lastMessageTime = it.lastMessageTime
        }
    }.toMutableList()
}