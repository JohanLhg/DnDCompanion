package com.jlahougue.roaming.presentation

import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_domain.model.HitDiceView
import com.jlahougue.item_presentation.InventoryState
import com.jlahougue.note.domain.model.Note
import com.jlahougue.skill_domain.model.SkillView
import com.jlahougue.user_info_domain.model.UnitSystem

data class RoamingState(
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val abilities: List<AbilityView> = emptyList(),
    val skills: List<SkillView> = emptyList(),
    val health: Health = Health(),
    val hitDice: HitDiceView = HitDiceView(),
    val inventory: InventoryState = InventoryState(),
    val notes: List<Note> = emptyList()
)
