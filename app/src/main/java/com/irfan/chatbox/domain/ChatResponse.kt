package com.irfan.chatbox.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatResponse(
    @Json(name = "userDetail")
    val userDetail: Profile,
    @Json(name = "inbox")
    val inbox: MutableList<Chat>
)