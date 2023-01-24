package com.irfan.chatbox.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Profile(
    @Json(name = "userId")
    val userId: String,
    @Json(name = "userName")
    val userName: String
)