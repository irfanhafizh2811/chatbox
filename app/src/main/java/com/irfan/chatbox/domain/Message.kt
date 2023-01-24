package com.irfan.chatbox.domain

import com.irfan.chatbox.database.DatabaseMessage
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Message(
    @Json(name = "senderId")
    val senderId: String,
    @Json(name = "message")
    val message: String,
    @Json(name = "time")
    val time: String,
    var messageId: String = ""
)

fun List<Message>.asDatabaseModel(messageId:String): List<DatabaseMessage> {
    return map {
        DatabaseMessage(
            messageId = messageId,
            time = it.time,
            senderId = it.senderId,
            message = it.message
        )
    }
}