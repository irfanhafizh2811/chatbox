package com.irfan.chatbox.domain

import com.irfan.chatbox.database.DatabaseChat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Chat(
    @Json(name = "senderId")
    val senderId: String,
    @Json(name = "senderName")
    val senderName: String,
    @Json(name = "messages")
    val messages: MutableList<Message> = mutableListOf()
) {
    var lastMessage: String = ""
    var lastMessageTime: String = ""
}

fun List<Chat>.asDatabaseModel(): List<DatabaseChat> {
    return map {
        DatabaseChat(
            senderId = it.senderId,
            senderName = it.senderName,
            lastMessage = it.messages.last().message,
            lastMessageTime = it.messages.last().time
        )
    }
}