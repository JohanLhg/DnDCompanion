package com.jlahougue.class_presentation.detail_dialog

sealed class ClassDialogEvent {
    data object OnDismiss : ClassDialogEvent()
}