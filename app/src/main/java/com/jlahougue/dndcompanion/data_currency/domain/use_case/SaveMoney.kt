package com.jlahougue.dndcompanion.data_currency.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_currency.domain.model.Money
import com.jlahougue.dndcompanion.data_currency.domain.repository.IMoneyRepository
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