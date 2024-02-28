package com.jlahougue.money_data.source.remote

import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.money_domain.model.Money

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