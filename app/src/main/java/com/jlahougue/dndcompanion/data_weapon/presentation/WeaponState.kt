package com.jlahougue.dndcompanion.data_weapon.presentation

import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogState

data class WeaponState(
    val weapons: List<WeaponInfo> = emptyList(),
    val listDialog: WeaponListDialogState = WeaponListDialogState(),
    val dialog: WeaponDialogState = WeaponDialogState()
)
