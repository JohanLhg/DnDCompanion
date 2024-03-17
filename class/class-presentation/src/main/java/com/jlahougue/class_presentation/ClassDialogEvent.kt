package com.jlahougue.class_presentation

sealed class ClassDialogEvent {
    data object OnDismiss : ClassDialogEvent()
}