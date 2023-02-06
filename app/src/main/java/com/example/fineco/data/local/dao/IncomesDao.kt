package com.example.fineco.data.local.dao

import androidx.room.*
import com.example.fineco.data.model.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomesDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(incomes: Income)

    @Query ("SELECT * FROM incomes_table WHERE type = :type")
    suspend fun get(type: String): Income

    @Query ("SELECT * FROM incomes_table")
    fun getAll(): Flow<List<Income>>

    @Update
    suspend fun update(incomes: Income)

    @Query("SELECT EXISTS(SELECT * FROM expenses_table WHERE type = :type)")
    suspend fun hasItem(type: String): Boolean
}