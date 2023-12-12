package com.jlahougue.dndcompanion.data_ability.domain.use_case

import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName

sealed class AbilityEvent {
    data class OnValueChange(
        val name: AbilityName,
        val value: Int
    ) : AbilityEvent()
    data class OnProficiencyChange(
        val name: AbilityName,
        val isProficient: Boolean
    ) : AbilityEvent()
}