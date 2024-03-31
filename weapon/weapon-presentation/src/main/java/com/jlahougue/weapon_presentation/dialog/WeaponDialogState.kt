package com.jlahougue.weapon_presentation.dialog

import com.jlahougue.weapon_domain.model.WeaponInfo

data class WeaponDialogState(
    val isShown: Boolean = false,
    val weapon: WeaponInfo? = null,
    val unitSystem: com.jlahougue.user_info_domain.model.UnitSystem = com.jlahougue.user_info_domain.model.UnitSystem.METRIC
)
