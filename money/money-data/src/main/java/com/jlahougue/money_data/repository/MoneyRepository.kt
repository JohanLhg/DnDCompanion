package com.jlahougue.money_data.repository

import com.jlahougue.money_data.source.local.MoneyLocalDataSource
import com.jlahougue.money_data.source.remote.MoneyRemoteDataSource
import com.jlahougue.money_domain.model.Money
import com.jlahougue.money_domain.repository.IMoneyRepository

class MoneyRepository(
    private val remote: MoneyRemoteDataSource,
    private val local: MoneyLocalDataSource
) : IMoneyRepository {
    override suspend fun create(characterId: Long) {
        local.insert(Money(characterId))
    }

    override suspend fun save(money: Money) {
        local.insert(money)
        remote.save(money)
    }

    override suspend fun saveToLocal(money: Money) = local.insert(money)

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) = local.delete(characterId)

    override fun get(characterId: Long) = local.get(characterId)
}