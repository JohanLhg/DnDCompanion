package com.jlahougue.dndcompanion.data_currency.domain.use_case

import com.jlahougue.dndcompanion.data_currency.domain.repository.IMoneyRepository

class GetMoney(private val repository: IMoneyRepository) {
    operator fun invoke(characterId: Long) = repository.get(characterId)
}