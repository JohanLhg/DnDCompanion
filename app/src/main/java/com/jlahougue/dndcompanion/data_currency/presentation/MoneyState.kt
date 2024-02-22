package com.jlahougue.dndcompanion.data_currency.presentation

import com.jlahougue.dndcompanion.data_currency.domain.model.Money
import com.jlahougue.dndcompanion.data_currency.presentation.dialog.MoneyDialogState

data class MoneyState(
    val money: Money = Money(),
    val dialog: MoneyDialogState = MoneyDialogState()
)
