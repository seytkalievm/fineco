package com.example.fineco.ui.budget.finances

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fineco.data.repository.FinancesRepository
import com.github.mikephil.charting.data.PieEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FinancesViewModel @Inject constructor(
    private val repository: FinancesRepository
): ViewModel() {
    private var _data: LiveData<List<PieEntry>> = MutableLiveData()
    val data get() = _data
    private var _totalAmount: LiveData<Float> = MutableLiveData()
    val totalAmount get() = _totalAmount

    fun setData(type: String) {
        _data = if (type == EXPENSES) repository.expenses else repository.incomes
        _totalAmount = if (type== EXPENSES) repository.totalExpenses else repository.totalIncomes
    }

}