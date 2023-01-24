package com.irfan.chatbox

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.irfan.chatbox.util.isTabletScreenLandscape
import com.irfan.chatbox.views.chat.ChatFragment
import com.irfan.chatbox.views.message.MessageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isTabletScreenLandscape()) {
            val chatFragment =
                supportFragmentManager.findFragmentById(R.id.chat_fragment) as ChatFragment
            val messageFragment =
                supportFragmentManager.findFragmentById(R.id.message_fragment) as MessageFragment
            chatFragment.setOnClickChatListener {
                messageFragment.apply { senderId = it.senderId }.getMessages()
            }
        }
    }
}