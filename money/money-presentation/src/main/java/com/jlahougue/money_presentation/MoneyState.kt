package com.jlahougue.money_presentation

import com.jlahougue.money_domain.model.Money
import com.jlahougue.money_presentation.dialog.MoneyDialogState

data class MoneyState(
    val money: Money = Money(),
    val dialog: MoneyDialogState = MoneyDialogState()
)
