package com.example.fineco.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "expenses_table")
data class Expense(
    @PrimaryKey
    val type: String,
    val amount: Float,
)