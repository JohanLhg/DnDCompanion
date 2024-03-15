package com.jlahougue.damage_type_presentation

sealed class DamageTypeDialogEvent {
    data object OnDismiss : DamageTypeDialogEvent()
}