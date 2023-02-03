package com.example.fineco.ui.finances

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fineco.R
import com.example.fineco.databinding.FragmentFinancesBinding


const val ARG_OBJECT = "fragment_type"
const val EXPENSES = "expenses"
const val INCOMES = "incomes"

class FinancesFragment : Fragment() {

    private lateinit var viewModel: FinancesViewModel
    private lateinit var binding: FragmentFinancesBinding
    private lateinit var type: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinancesBinding.inflate(inflater, container, false)
        type = arguments?.getString(ARG_OBJECT)!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView4.text =
            if (type == EXPENSES) getString(R.string.expenses)
            else getString(R.string.incomes)
    }
}