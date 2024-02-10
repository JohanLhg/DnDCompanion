package com.jlahougue.dndcompanion.data_currency.presentation

sealed class MoneyEvent {
    data class OnOtherCurrenciesChanged(val otherCurrencies: String) : MoneyEvent()
}