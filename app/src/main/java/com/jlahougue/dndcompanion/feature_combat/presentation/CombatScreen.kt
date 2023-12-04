package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.compose.runtime.Composable
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellLevel

@Composable
fun CombatScreen(
    abilities: List<AbilityView>
) {
    SpellLevel()
}