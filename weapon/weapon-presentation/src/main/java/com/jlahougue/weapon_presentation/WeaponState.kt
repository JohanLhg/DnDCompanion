package com.jlahougue.weapon_presentation

import com.jlahougue.weapon_domain.model.WeaponInfo
import com.jlahougue.weapon_presentation.dialog.WeaponDialogState
import com.jlahougue.weapon_presentation.list_dialog.WeaponListDialogState

data class WeaponState(
    val weapons: List<WeaponInfo> = emptyList(),
    val listDialog: WeaponListDialogState = WeaponListDialogState(),
    val dialog: WeaponDialogState = WeaponDialogState()
)
