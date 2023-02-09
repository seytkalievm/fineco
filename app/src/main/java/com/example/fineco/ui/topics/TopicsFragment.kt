package com.example.fineco.ui.topics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fineco.R
import com.example.fineco.databinding.FragmentTopicsBinding
import com.example.fineco.ui.util.Button
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicsFragment : Fragment() {

    private val viewModel: TopicsViewModel by viewModels()
    private lateinit var binding: FragmentTopicsBinding
    private val args: TopicsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionTitle = args.sectionTitle
        val navController = findNavController()
        val topics = viewModel.getTopics(sectionTitle)
        Log.i("TopicsFragment", "onViewCreated: $topics")
        binding.appbarTv.text = sectionTitle
            for(i in topics.indices) {
            context?.let {
                val args = bundleOf("topic" to topics[i])
                binding.buttonsLl.addView(Button(requireActivity(), topics[i], it) {
                    navController.navigate(R.id.action_topicsFragment_to_topicVideoFragment, args)
                })
            }
        }
    }


}