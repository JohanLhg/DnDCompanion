package com.jlahougue.money_domain.use_case

import com.jlahougue.money_domain.repository.IMoneyRepository

class GetMoney(private val repository: IMoneyRepository) {
    operator fun invoke(characterId: Long) = repository.get(characterId)
}