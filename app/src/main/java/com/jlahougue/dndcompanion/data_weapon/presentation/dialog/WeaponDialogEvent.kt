package com.jlahougue.dndcompanion.data_weapon.presentation.dialog

import com.jlahougue.dndcompanion.data_property.domain.model.Property
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo

sealed class WeaponDialogEvent {
    data object OnDismiss : WeaponDialogEvent()
    data class OnCountChange(val weaponInfo: WeaponInfo, val count: Int) : WeaponDialogEvent()
    data class OnPropertyClick(val property: Property) : WeaponDialogEvent()
}