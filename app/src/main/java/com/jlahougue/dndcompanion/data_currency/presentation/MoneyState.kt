package com.jlahougue.dndcompanion.data_currency.presentation

import com.jlahougue.dndcompanion.data_currency.presentation.dialog.MoneyDialogState
import com.jlahougue.money_domain.model.Money

data class MoneyState(
    val money: Money = Money(),
    val dialog: MoneyDialogState = MoneyDialogState()
)
