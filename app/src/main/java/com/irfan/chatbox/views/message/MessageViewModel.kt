package com.irfan.chatbox.views.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.irfan.chatbox.domain.Message
import com.irfan.chatbox.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {

    fun getMessages(messageId: String): LiveData<MutableList<Message>> =
        messageRepository.getMessages(messageId)

}