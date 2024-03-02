package com.jlahougue.weapon_presentation.list_dialog

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.weapon_domain.model.WeaponInfo

data class WeaponListDialogState(
    val isShown: Boolean = false,
    val search: String = "",
    val filter: Filter = Filter.ALL,
    val weapons: List<WeaponInfo> = listOf(),
    val unitSystem: UnitSystem = UnitSystem.METRIC
) {
    enum class Filter(val value: AbilityName? = null) {
        ALL,
        MELEE(AbilityName.STRENGTH),
        RANGED(AbilityName.DEXTERITY),
        MAGIC(AbilityName.NONE)
    }
}
