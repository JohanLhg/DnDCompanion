package com.jlahougue.dndcompanion.data_character_spell.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel

@Composable
fun SpellList(
    spells: List<SpellLevel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = spells,
            key = { it.spellSlot.level }
        ) {
            SpellLevelSection(
                spellLevel = it
            )
        }
    }
}