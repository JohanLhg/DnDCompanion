package com.jlahougue.weapon_presentation.dialog

import com.jlahougue.property_domain.model.Property
import com.jlahougue.weapon_domain.model.WeaponInfo

sealed class WeaponDialogEvent {
    data object OnDismiss : WeaponDialogEvent()
    data class OnCountChange(val weaponInfo: WeaponInfo, val count: Int) : WeaponDialogEvent()
    data class OnPropertyClick(val property: Property) : WeaponDialogEvent()
}