package com.jlahougue.weapon_presentation.list_dialog

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.weapon_domain.model.WeaponInfo

data class WeaponListDialogState(
    val isShown: Boolean = false,
    val search: String = "",
    val filter: Filter = Filter.ALL,
    val weapons: List<WeaponInfo> = listOf(),
    val unitSystem: com.jlahougue.user_info_domain.model.UnitSystem = com.jlahougue.user_info_domain.model.UnitSystem.METRIC
) {
    enum class Filter(val value: AbilityName? = null) {
        ALL,
        MELEE(AbilityName.STRENGTH),
        RANGED(AbilityName.DEXTERITY),
        MAGIC(AbilityName.NONE)
    }
}
