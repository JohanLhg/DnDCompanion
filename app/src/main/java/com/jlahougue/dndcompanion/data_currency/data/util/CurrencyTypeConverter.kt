package com.jlahougue.dndcompanion.data_currency.data.util

import androidx.room.TypeConverter
import com.jlahougue.money_domain.model.Currency

class CurrencyTypeConverter {
    @TypeConverter
    fun toCurrency(value: String) = Currency.from(value)

    @TypeConverter
    fun fromCurrency(value: Currency) = value.name
}