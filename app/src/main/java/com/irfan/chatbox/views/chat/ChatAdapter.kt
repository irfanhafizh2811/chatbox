package com.irfan.chatbox.views.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.irfan.chatbox.databinding.ListItemChatBinding
import com.irfan.chatbox.domain.Chat
import javax.inject.Inject

class ChatAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<Chat, ChatAdapter.ViewHolder>(ChatListDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ListItemChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Chat, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemChatBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ChatListDiffCallback : DiffUtil.ItemCallback<Chat>() {

    override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean =
        oldItem.senderId == newItem.senderId

    override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean = oldItem == newItem

}

class ClickListener @Inject constructor() {

    var onItemClick: ((Chat) -> Unit)? = null

    fun onClick(data: Chat) {
        onItemClick?.invoke(data)
    }
}