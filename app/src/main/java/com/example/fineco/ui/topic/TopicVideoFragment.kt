package com.example.fineco.ui.topic

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fineco.databinding.FragmentTopicVideoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicVideoFragment : Fragment() {

    private val viewModel: TopicVideoViewModel by viewModels()
    private lateinit var binding: FragmentTopicVideoBinding
    private val args: TopicVideoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopicVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topic = args.topic
        val videoUrl = viewModel.getVideoByTopic(topic)
        Log.i("TopicVideoFragment", "onViewCreated: $videoUrl")

        binding.textView2.text = videoUrl
    }

}