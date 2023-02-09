package com.example.fineco.ui.topic

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fineco.databinding.FragmentTopicVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TopicVideoFragment : Fragment() {

    private val viewModel: TopicVideoViewModel by viewModels()
    private lateinit var binding: FragmentTopicVideoBinding
    private val args: TopicVideoFragmentArgs by navArgs()
    private lateinit var player: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopicVideoBinding.inflate(inflater, container, false)
        player = ExoPlayer.Builder(requireContext()).build()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topic = args.topic
        val videoUrl = viewModel.getVideoByTopic(topic)
        Log.i("TopicVideoFragment", "onViewCreated: $videoUrl")
        binding.appbarTitle.text = topic
        binding.button.setOnClickListener {
            findNavController().popBackStack()
        }
        val video = getUriFromRawFile(requireContext(), videoUrl ?: "")
        video?.let { player.setMediaItem(MediaItem.fromUri(it)) }
        binding.videoView.player = player
        binding.videoView.controllerHideOnTouch = true
        binding.videoView.controllerShowTimeoutMs = 1500
        player.prepare()
        player.play()
    }

    override fun onStop() {
        super.onStop()
        player.stop()
    }
    private fun getUriFromRawFile(context: Context, path: String): Uri? {
        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(context.packageName)
            .path(path)
            .build()
    }

    override fun onDestroyView() {
        player.release()
        super.onDestroyView()
    }
}