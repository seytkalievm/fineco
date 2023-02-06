package com.example.fineco.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.example.fineco.data.local.dao.ExpensesDao
import com.example.fineco.data.local.dao.IncomesDao
import com.example.fineco.data.model.Expense
import com.example.fineco.data.model.Income
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FinancesRepository @Inject constructor(
    private val incomesDao: IncomesDao,
    private val expensesDao: ExpensesDao,
){
    private val _expenses = expensesDao.getAll()
    val expenses: LiveData<List<PieEntry>> = _expenses.asLiveData().map { list ->
        list.map { expense ->  PieEntry(expense.amount, expense.type)}
    }

    private val _incomes = incomesDao.getAll()
    val incomes: LiveData<List<PieEntry>> = _incomes.asLiveData().map { list ->
        list.map { expense ->  PieEntry(expense.amount, expense.type)}
    }

    val totalExpenses = _expenses.map {list ->
        var total = 0f
        list.map { expense -> total += expense.amount }
        total
    }.asLiveData()

    val totalIncomes = _incomes.map {list ->
        var total = 0f
        list.map { income -> total += income.amount }
        total
    }.asLiveData()

    suspend fun addExpense(expense: Expense){
        if (expensesDao.hasItem(expense.type)) {
            expensesDao.update(expense)
        } else {
            expensesDao.add(expense)
        }
    }

    suspend fun addIncome(income: Income) {
        if (incomesDao.hasItem(income.type)) {
            incomesDao.update(income)
        } else {
            incomesDao.add(income)
        }
    }


}