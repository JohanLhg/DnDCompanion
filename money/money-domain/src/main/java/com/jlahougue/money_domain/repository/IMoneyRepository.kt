package com.jlahougue.money_domain.repository

import com.jlahougue.money_domain.model.Money
import kotlinx.coroutines.flow.Flow

interface IMoneyRepository {
    suspend fun create(characterId: Long)
    suspend fun save(money: Money)
    suspend fun saveToLocal(money: Money)
    suspend fun delete(characterId: Long)
    fun get(characterId: Long): Flow<Money>
}