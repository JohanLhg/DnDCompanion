package com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog

import com.jlahougue.weapon_domain.model.WeaponInfo

data class WeaponListDialogState(
    val isShown: Boolean = false,
    val search: String = "",
    val filter: Filter = Filter.ALL,
    val weapons: List<WeaponInfo> = listOf(),
    val unitSystem: com.jlahougue.settings_domain.model.UnitSystem = com.jlahougue.settings_domain.model.UnitSystem.METRIC
) {
    enum class Filter(val value: com.jlahougue.ability_domain.model.AbilityName? = null) {
        ALL,
        MELEE(com.jlahougue.ability_domain.model.AbilityName.STRENGTH),
        RANGED(com.jlahougue.ability_domain.model.AbilityName.DEXTERITY),
        MAGIC(com.jlahougue.ability_domain.model.AbilityName.NONE)
    }
}
