package com.jlahougue.money_presentation.dialog

data class MoneyDialogState(
    val isShown: Boolean = false,
    val type: MoneyDialogType = MoneyDialogType.ADD,
    val copperPieces: Int = 0,
    val silverPieces: Int = 0,
    val goldPieces: Int = 0,
    val platinumPieces: Int = 0
) {
    enum class MoneyDialogType {
        ADD,
        SET,
        SUBTRACT
    }
}
