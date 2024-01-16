package com.jlahougue.dndcompanion.data_weapon.presentation.dialog

import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo

data class WeaponDialogState(
    val isShown: Boolean = false,
    val weapon: WeaponInfo? = null,
    val unitSystem: UnitSystem = UnitSystem.METRIC
)
