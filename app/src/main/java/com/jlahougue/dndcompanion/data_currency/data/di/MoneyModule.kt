package com.jlahougue.dndcompanion.data_currency.data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_currency.data.repository.MoneyRepository
import com.jlahougue.dndcompanion.data_currency.data.source.local.MoneyLocalDataSource
import com.jlahougue.dndcompanion.data_currency.data.source.remote.MoneyRemoteDataSource
import com.jlahougue.money_domain.di.IMoneyModule
import com.jlahougue.money_domain.use_case.GetMoney
import com.jlahougue.money_domain.use_case.MoneyUseCases
import com.jlahougue.money_domain.use_case.SaveMoney

class MoneyModule(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: MoneyRemoteDataSource,
    private val localDataSource: MoneyLocalDataSource
) : IMoneyModule {
    override val repository: MoneyRepository by lazy {
        MoneyRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases: MoneyUseCases by lazy {
        MoneyUseCases(
            SaveMoney(dispatcher, repository),
            GetMoney(repository)
        )
    }
}