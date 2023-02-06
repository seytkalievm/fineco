package com.example.fineco.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fineco.data.local.dao.ExpensesDao
import com.example.fineco.data.local.dao.IncomesDao
import com.example.fineco.data.model.Expense
import com.example.fineco.data.model.Income

@Database (entities = [Expense::class, Income::class], version = 1)
abstract class FinancesDatabase: RoomDatabase() {
    abstract val expensesDao: ExpensesDao
    abstract val incomesDao: IncomesDao

    companion object {
        @Volatile
        var INSTANCE: FinancesDatabase? = null

        fun getInstance(context: Context): FinancesDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FinancesDatabase::class.java, "finances_database",)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}