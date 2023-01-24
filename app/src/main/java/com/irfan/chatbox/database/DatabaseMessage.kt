package com.irfan.chatbox.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.irfan.chatbox.domain.Message

@Entity
data class DatabaseMessage constructor(
    @PrimaryKey
    val time: String,
    val senderId: String,
    val message: String,
    val messageId: String
)

fun List<DatabaseMessage>.asDomainModel(): MutableList<Message> {
    return map {
        Message(
            time = it.time,
            senderId = it.senderId,
            message = it.message
        )
    }.toMutableList()
}