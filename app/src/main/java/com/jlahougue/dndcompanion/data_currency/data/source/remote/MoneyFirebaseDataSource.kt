package com.jlahougue.dndcompanion.data_currency.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.FirebaseDataSource
import com.jlahougue.dndcompanion.data_currency.domain.model.Money

class MoneyFirebaseDataSource(
    private val dataSource: FirebaseDataSource
) : MoneyRemoteDataSource {
    override suspend fun save(money: Money) {
        dataSource.updateCharacterSheet(
            money.cid,
            mapOf("money" to money)
        )
    }
}