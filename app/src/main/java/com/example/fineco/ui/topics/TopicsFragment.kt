package com.example.fineco.ui.topics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fineco.R
import com.example.fineco.data.Sections
import com.example.fineco.databinding.FragmentTopicsBinding
import com.example.fineco.ui.util.Button

class TopicsFragment : Fragment() {

    private lateinit var viewModel: TopicsViewModel
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
        val id = args.sectionNumber
        val navController = findNavController()
        val topics = Sections.getSectionTopics(id)
        binding.appbarTv.text = getString(R.string.section, id)
        for(i in topics.indices) {
            context?.let {
                val args = bundleOf("video_url" to topics[i].video)
                binding.buttonsLl.addView(Button(requireActivity(), topics[i].title, it) {
                    navController.navigate(R.id.action_topicsFragment_to_topicVideoFragment, args)
                })
            }
        }
    }


}