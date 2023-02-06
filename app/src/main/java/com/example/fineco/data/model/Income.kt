package com.example.fineco.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "incomes_table")
data class Income(
    @PrimaryKey val type: String,
    val amount: Float
)