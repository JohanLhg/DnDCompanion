package com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog

import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo

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
