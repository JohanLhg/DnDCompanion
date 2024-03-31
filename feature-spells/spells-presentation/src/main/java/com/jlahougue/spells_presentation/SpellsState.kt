package com.jlahougue.spells_presentation

import com.jlahougue.character_spell_domain.model.CharacterSpellsStatsView
import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.character_spell_domain.model.SpellLevel
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.character_spell_presentation.components.SpellListMode
import com.jlahougue.character_spell_presentation.dialog.SpellDialogState
import com.jlahougue.damage_type_presentation.DamageTypeDialogState
import com.jlahougue.spells_presentation.components.SpellSearchState

data class SpellsState(
    val spellcasting: SpellcasterView = SpellcasterView(),
    val spellsStats: CharacterSpellsStatsView = CharacterSpellsStatsView(),
    val search: SpellSearchState = SpellSearchState(),
    val classes: List<String> = emptyList(),
    val spellLevels: List<Int> = emptyList(),
    val allSpells: List<SpellInfo> = emptyList(),
    val knownSpells: List<SpellLevel> = emptyList(),
    val mode: SpellListMode = SpellListMode.Known,
    val spellDialog: SpellDialogState = SpellDialogState(),
    val damageTypeDialog: DamageTypeDialogState = DamageTypeDialogState()
)
