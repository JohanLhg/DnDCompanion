package com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog

sealed class WeaponListDialogEvent {
    data object OnDismiss : WeaponListDialogEvent()
    data class OnSearchChange(val search: String) : WeaponListDialogEvent()
}