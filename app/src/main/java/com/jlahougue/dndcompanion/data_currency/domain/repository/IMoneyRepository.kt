package com.jlahougue.dndcompanion.data_currency.domain.repository

import com.jlahougue.dndcompanion.data_currency.domain.model.Money
import kotlinx.coroutines.flow.Flow

interface IMoneyRepository {
    suspend fun save(money: Money)
    suspend fun saveToLocal(money: Money)
    fun get(characterId: Long): Flow<Money>
}