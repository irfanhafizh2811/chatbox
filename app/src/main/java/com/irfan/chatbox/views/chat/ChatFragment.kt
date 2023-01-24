package com.irfan.chatbox.views.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irfan.chatbox.R
import com.irfan.chatbox.databinding.FragmentChatBinding
import com.irfan.chatbox.domain.Chat
import com.irfan.chatbox.util.isTabletScreenLandscape
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment() {
    private val viewModel: ChatViewModel by viewModels()

    @Inject
    lateinit var adapter: ChatAdapter

    private var _binding: FragmentChatBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var onClickChatListener: ((Chat) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_chat, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        adapter.clickListener.onItemClick = {
            if (!requireActivity().isTabletScreenLandscape())
                findNavController().navigate(ChatFragmentDirections.actionPageChatToPageMessage(it.senderId))
            else onClickChatListener?.invoke(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

    fun setOnClickChatListener(onClickChatListener: (Chat) -> Unit) {
        this.onClickChatListener = onClickChatListener
    }
}