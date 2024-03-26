package com.jlahougue.money_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.money_domain.model.Money
import com.jlahougue.money_domain.repository.IMoneyRepository

class MoneyRepository(
    private val remote: RemoteUserDataSource,
    private val local: MoneyLocalDataSource
) : IMoneyRepository {
    override suspend fun create(characterId: Long) {
        local.insert(Money(characterId))
    }

    override suspend fun save(money: Money) {
        local.insert(money)
        remote.updateDocument(
            remote.characterUrl(money.cid),
            mapOf("money" to money)
        )
    }

    override suspend fun saveToLocal(money: Money) = local.insert(money)

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterId: Long) = local.delete(characterId)

    override fun get(characterId: Long) = local.get(characterId)
}