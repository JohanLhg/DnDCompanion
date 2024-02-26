package com.jlahougue.dndcompanion.data_weapon.presentation.dialog

import com.jlahougue.weapon_domain.model.WeaponInfo

data class WeaponDialogState(
    val isShown: Boolean = false,
    val weapon: WeaponInfo? = null,
    val unitSystem: com.jlahougue.settings_domain.model.UnitSystem = com.jlahougue.settings_domain.model.UnitSystem.METRIC
)
