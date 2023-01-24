package com.irfan.chatbox.views.message

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.irfan.chatbox.databinding.ListItemMessageReceivedBinding
import com.irfan.chatbox.databinding.ListItemMessageSentBinding
import com.irfan.chatbox.domain.Message
import javax.inject.Inject

class MessageAdapter @Inject constructor() :
    ListAdapter<Message, ViewHolder>(MessageListDiffCallback()) {

    var senderId: String = ""

    companion object {
        const val RECEIVED_TYPE = 1
        const val SENT_TYPE = 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ViewHolderMessageReceived -> holder.bind(item)
            is ViewHolderMessageSent -> holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            RECEIVED_TYPE -> ViewHolderMessageReceived(
                ListItemMessageReceivedBinding.inflate(layoutInflater, parent, false)
            )
            SENT_TYPE -> ViewHolderMessageSent(
                ListItemMessageSentBinding.inflate(layoutInflater, parent, false)
            )
            else ->ViewHolderMessageReceived(
                ListItemMessageReceivedBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

    override fun getItemViewType(position: Int): Int = when {
        senderId == getItem(position).senderId -> RECEIVED_TYPE
        else -> SENT_TYPE
    }

    class ViewHolderMessageReceived constructor(private val binding: ListItemMessageReceivedBinding) :
        ViewHolder(binding.root) {

        fun bind(item: Message) {
            binding.data = item
            binding.executePendingBindings()
        }
    }

    class ViewHolderMessageSent constructor(private val binding: ListItemMessageSentBinding) :
        ViewHolder(binding.root) {

        fun bind(item: Message) {
            binding.data = item
            binding.executePendingBindings()
        }
    }
}

class MessageListDiffCallback : DiffUtil.ItemCallback<Message>() {

    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean =
        oldItem.time == newItem.time

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean =
        oldItem == newItem

}