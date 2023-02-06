package com.example.fineco.ui.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fineco.ui.budget.finances.ARG_OBJECT
import com.example.fineco.ui.budget.finances.EXPENSES
import com.example.fineco.ui.budget.finances.FinancesFragment
import com.example.fineco.ui.budget.finances.INCOMES

class FinancesPageAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FinancesFragment()
        val type = if (position == 0) EXPENSES else INCOMES
        fragment.arguments = Bundle().apply {
            putString(ARG_OBJECT, type)
        }

        return fragment
    }

}