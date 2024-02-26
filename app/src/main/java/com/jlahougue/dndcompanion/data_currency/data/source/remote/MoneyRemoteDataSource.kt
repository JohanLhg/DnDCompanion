package com.jlahougue.dndcompanion.data_currency.data.source.remote

import com.jlahougue.money_domain.model.Money

interface MoneyRemoteDataSource {
    suspend fun save(money: Money)
}