package com.jlahougue.authentication_presentation.email_change_dialog

sealed class EmailChangeDialogEvent {
    data object OnDismiss : EmailChangeDialogEvent()
    data class OnEmailChange(val email: String) : EmailChangeDialogEvent()
    data object OnConfirm : EmailChangeDialogEvent()
}
