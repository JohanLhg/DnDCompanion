package com.jlahougue.money_data.repository

import com.jlahougue.money_data.source.local.MoneyLocalDataSource
import com.jlahougue.money_data.source.remote.MoneyRemoteDataSource
import com.jlahougue.money_domain.model.Money
import com.jlahougue.money_domain.repository.IMoneyRepository

class MoneyRepository(
    private val remoteDataSource: MoneyRemoteDataSource,
    private val localDataSource: MoneyLocalDataSource
) : IMoneyRepository {
    override suspend fun create(characterId: Long) {
        localDataSource.insert(Money(characterId))
    }

    override suspend fun save(money: Money) {
        localDataSource.insert(money)
        remoteDataSource.save(money)
    }

    override suspend fun saveToLocal(money: Money) {
        localDataSource.insert(money)
    }

    override suspend fun delete(characterId: Long) {
        localDataSource.delete(characterId)
    }

    override fun get(characterId: Long) = localDataSource.get(characterId)
}