package com.jlahougue.dndcompanion.data_currency.di

import com.jlahougue.dndcompanion.data_currency.domain.repository.IMoneyRepository
import com.jlahougue.dndcompanion.data_currency.domain.use_case.MoneyUseCases

interface IMoneyModule {
    val repository: IMoneyRepository
    val useCases: MoneyUseCases
}