package com.example.fineco.ui.budget

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fineco.R
import com.example.fineco.databinding.FragmentBudgetBinding
import com.example.fineco.ui.util.FinancesPageAdapter
import com.google.android.material.tabs.TabLayoutMediator

class BudgetFragment : Fragment() {
    private lateinit var binding: FragmentBudgetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayoutAdapter = FinancesPageAdapter(this)
        val viewPager = binding.pager
        viewPager.adapter = tabLayoutAdapter
        val tabLayout = binding.tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text =
                if (position == 0) getString(R.string.expenses) else getString(R.string.incomes)
        }.attach()
    }


}