package com.example.fineco.data.local.dao

import androidx.room.*
import com.example.fineco.data.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpensesDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(expense: Expense)

    @Query("SELECT * FROM expenses_table")
    fun getAll(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses_table WHERE type = :type")
    suspend fun get(type: String): Expense

    @Update
    suspend fun update(expense: Expense)

    @Query("SELECT EXISTS(SELECT * FROM expenses_table WHERE type = :type)")
    suspend fun hasItem(type: String): Boolean

}