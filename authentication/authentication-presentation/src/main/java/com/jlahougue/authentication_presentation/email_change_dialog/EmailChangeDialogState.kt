package com.jlahougue.authentication_presentation.email_change_dialog

data class EmailChangeDialogState(
    val isShown: Boolean = false,
    val email: String = "",
    val isEmailValid: Boolean = true
)
