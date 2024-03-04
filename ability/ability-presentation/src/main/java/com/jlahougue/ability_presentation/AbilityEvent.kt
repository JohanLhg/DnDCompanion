package com.jlahougue.ability_presentation

import com.jlahougue.ability_domain.model.AbilityName

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