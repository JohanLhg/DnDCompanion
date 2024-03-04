package com.jlahougue.money_presentation

import com.jlahougue.money_domain.model.Money

sealed class MoneyEvent {
    data object OnDialogOpen : MoneyEvent()
    data class OnOtherCurrenciesChanged(
        val money: Money,
        val otherCurrencies: String
    ) : MoneyEvent()
}