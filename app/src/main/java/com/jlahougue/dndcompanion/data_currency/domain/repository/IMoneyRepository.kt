package com.jlahougue.dndcompanion.data_currency.domain.repository

import com.jlahougue.dndcompanion.data_currency.domain.model.Money
import kotlinx.coroutines.flow.Flow

interface IMoneyRepository {
    suspend fun create(characterId: Long)
    suspend fun save(money: Money)
    suspend fun saveToLocal(money: Money)
    suspend fun delete(characterId: Long)
    fun get(characterId: Long): Flow<Money>
}