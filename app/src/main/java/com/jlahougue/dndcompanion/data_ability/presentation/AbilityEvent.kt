package com.jlahougue.dndcompanion.data_ability.presentation

sealed class AbilityEvent {
    data class OnValueChange(
        val name: com.jlahougue.ability_domain.model.AbilityName,
        val value: Int
    ) : AbilityEvent()
    data class OnProficiencyChange(
        val name: com.jlahougue.ability_domain.model.AbilityName,
        val isProficient: Boolean
    ) : AbilityEvent()
}