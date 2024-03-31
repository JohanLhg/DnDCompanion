package com.jlahougue.weapon_presentation.list_dialog

sealed class WeaponListDialogEvent {
    data object OnDismiss : WeaponListDialogEvent()
    data class OnSearchChange(val search: String) : WeaponListDialogEvent()
    data class OnFilterChange(val filter: WeaponListDialogState.Filter) : WeaponListDialogEvent()
}