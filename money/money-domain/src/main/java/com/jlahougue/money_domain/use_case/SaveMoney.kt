package com.jlahougue.money_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.money_domain.model.Money
import com.jlahougue.money_domain.repository.IMoneyRepository
import kotlinx.coroutines.withContext

class SaveMoney(
    private val dispatcherProvider: DispatcherProvider,
    private val moneyRepository: IMoneyRepository
) {
    suspend operator fun invoke(money: Money) {
        withContext(dispatcherProvider.io) {
            moneyRepository.save(money)
        }
    }
}