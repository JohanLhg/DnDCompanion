package com.jlahougue.money_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.money_domain.di.IMoneyModule
import com.jlahougue.money_domain.use_case.GetMoney
import com.jlahougue.money_domain.use_case.MoneyUseCases
import com.jlahougue.money_domain.use_case.SaveMoney

class MoneyModule(
    dispatcher: DispatcherProvider,
    remoteDataSource: RemoteUserDataSource,
    localDataSource: MoneyLocalDataSource
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