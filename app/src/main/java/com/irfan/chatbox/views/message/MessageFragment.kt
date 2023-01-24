package com.irfan.chatbox.views.message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.irfan.chatbox.R
import com.irfan.chatbox.databinding.FragmentMessageBinding
import com.irfan.chatbox.pref.ApplicationPref
import com.irfan.chatbox.util.isTabletScreenLandscape
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MessageFragment : Fragment() {

    private val args: MessageFragmentArgs by navArgs()
    private val viewModel: MessageViewModel by viewModels()

    @Inject
    lateinit var adapter: MessageAdapter

    @Inject
    lateinit var applicationPref: ApplicationPref

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!
    var senderId = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_message, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        if (!requireActivity().isTabletScreenLandscape()) {
            senderId = args.senderId.orEmpty()
        }
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMessages()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

    fun getMessages() {
        val messageId = applicationPref.userId.plus(senderId)
        adapter.senderId = senderId
        viewModel.getMessages(messageId).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}