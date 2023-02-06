package com.example.fineco.di

import android.content.Context
import com.example.fineco.data.local.FinancesDatabase
import com.example.fineco.data.local.dao.ExpensesDao
import com.example.fineco.data.local.dao.IncomesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FinancesDatabase {
        return FinancesDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideExpensesDao(db: FinancesDatabase): ExpensesDao {
        return db.expensesDao
    }

    @Singleton
    @Provides
    fun provideIncomeDao(db: FinancesDatabase): IncomesDao {
        return db.incomesDao
    }
}