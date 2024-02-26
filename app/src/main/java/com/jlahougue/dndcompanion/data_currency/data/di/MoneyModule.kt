package com.jlahougue.dndcompanion.data_currency.data.di

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_currency.data.repository.MoneyRepository
import com.jlahougue.money_domain.di.IMoneyModule
import com.jlahougue.money_domain.use_case.GetMoney
import com.jlahougue.money_domain.use_case.MoneyUseCases
import com.jlahougue.money_domain.use_case.SaveMoney

class MoneyModule(
    private val dispatcher: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IMoneyModule {
    override val repository: MoneyRepository by lazy {
        MoneyRepository(
            remoteDataSource.moneyDao,
            localDataSource.moneyDao()
        )
    }

    override val useCases: MoneyUseCases by lazy {
        MoneyUseCases(
            SaveMoney(dispatcher, repository),
            GetMoney(repository)
        )
    }
}