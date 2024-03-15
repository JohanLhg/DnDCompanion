package com.jlahougue.property_presentation

sealed class PropertyDialogEvent {
    data object OnDismiss : PropertyDialogEvent()
}
