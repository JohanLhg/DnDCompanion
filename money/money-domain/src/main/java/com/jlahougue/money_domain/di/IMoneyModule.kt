package com.jlahougue.money_domain.di

import com.jlahougue.money_domain.repository.IMoneyRepository
import com.jlahougue.money_domain.use_case.MoneyUseCases

interface IMoneyModule {
    val repository: IMoneyRepository
    val useCases: MoneyUseCases
}