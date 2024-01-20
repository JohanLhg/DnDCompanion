package com.jlahougue.dndcompanion.data_currency.domain.util

import androidx.room.TypeConverter
import com.jlahougue.dndcompanion.data_currency.domain.model.Currency

class CurrencyTypeConverter {
    @TypeConverter
    fun toCurrency(value: String) = Currency.from(value)

    @TypeConverter
    fun fromCurrency(value: Currency) = value.name
}