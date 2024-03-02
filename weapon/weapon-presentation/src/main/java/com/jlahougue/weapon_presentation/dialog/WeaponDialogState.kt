package com.jlahougue.weapon_presentation.dialog

import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.weapon_domain.model.WeaponInfo

data class WeaponDialogState(
    val isShown: Boolean = false,
    val weapon: WeaponInfo? = null,
    val unitSystem: UnitSystem = UnitSystem.METRIC
)
