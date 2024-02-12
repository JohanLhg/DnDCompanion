package com.jlahougue.dndcompanion.data_currency.presentation.dialog

sealed class MoneyDialogEvent {
    data class OnTypeChanged(val type: MoneyDialogState.MoneyDialogType) : MoneyDialogEvent()
    data class OnCopperPiecesChanged(val copperPieces: Int) : MoneyDialogEvent()
    data class OnSilverPiecesChanged(val silverPieces: Int) : MoneyDialogEvent()
    data class OnGoldPiecesChanged(val goldPieces: Int) : MoneyDialogEvent()
    data class OnPlatinumPiecesChanged(val platinumPieces: Int) : MoneyDialogEvent()
    data object OnDismiss : MoneyDialogEvent()
    data object OnConfirm : MoneyDialogEvent()
}
