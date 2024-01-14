package com.jlahougue.dndcompanion.data_weapon.presentation.dialog

import com.jlahougue.dndcompanion.data_property.domain.model.Property

sealed class WeaponDialogEvent {
    data object OnDismiss : WeaponDialogEvent()
    data class OnPropertyClick(val property: Property) : WeaponDialogEvent()
}