package com.irfan.chatbox.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "response")
    val chatResponse: ChatResponse
)