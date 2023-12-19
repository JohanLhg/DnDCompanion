package com.jlahougue.dndcompanion.data_character_spell.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellLevelSection

@Composable
fun SpellList(
    spells: List<SpellLevel>,
    modifier: Modifier = Modifier,
    setSpellState: ((SpellInfo, SpellState) -> Unit)? = null
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = spells,
            key = { it.spellSlot.level }
        ) {
            SpellLevelSection(
                setSpellState = setSpellState,
                spellLevel = it
            )
        }
    }
}