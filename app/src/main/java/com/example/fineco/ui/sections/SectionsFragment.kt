package com.example.fineco.ui.sections

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fineco.R
import com.example.fineco.data.model.Sections
import com.example.fineco.databinding.FragmentSectionsBinding
import com.example.fineco.ui.util.Button
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionsFragment : Fragment() {

    private val viewModel: SectionsViewModel by viewModels()
    private lateinit var binding: FragmentSectionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSectionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sections = Sections.getSections()
        val navController = findNavController()
        for(i in sections.indices) {
            context?.let {
                val args = bundleOf("section_number" to i)
                binding.buttonsLl.addView(Button(requireActivity(), sections[i], it) {
                    navController.navigate(R.id.action_sectionsFragment_to_topicsFragment, args)
                })
            }
        }

    }


}