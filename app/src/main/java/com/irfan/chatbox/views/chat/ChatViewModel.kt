package com.irfan.chatbox.views.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfan.chatbox.pref.ApplicationPref
import com.irfan.chatbox.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val applicationPref: ApplicationPref
) : ViewModel() {

    val data = chatRepository.chats

    init {
        viewModelScope.launch(Dispatchers.IO) {
            chatRepository.refreshChats()
            applicationPref.hasSyncData = true
        }
    }
}