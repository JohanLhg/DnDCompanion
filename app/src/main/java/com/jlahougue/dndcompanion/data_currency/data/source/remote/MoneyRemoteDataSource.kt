package com.jlahougue.dndcompanion.data_currency.data.source.remote

import com.jlahougue.dndcompanion.data_currency.domain.model.Money

interface MoneyRemoteDataSource {
    suspend fun save(money: Money)
}