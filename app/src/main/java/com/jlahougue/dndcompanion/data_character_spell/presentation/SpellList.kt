package com.jlahougue.dndcompanion.data_character_spell.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellLevelSection
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode

@Composable
fun SpellList(
    spells: List<SpellLevel>,
    modifier: Modifier = Modifier,
    mode: SpellListMode
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = spells,
            key = { it.spellSlot.level }
        ) {
            SpellLevelSection(
                spellLevel = it,
                mode = mode
            )
        }
    }
}