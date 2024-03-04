package com.jlahougue.character_spell_presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.character_spell_domain.model.SpellLevel
import com.jlahougue.character_spell_presentation.components.SpellLevelSection
import com.jlahougue.character_spell_presentation.components.SpellListMode

@Composable
fun SpellLevelList(
    spells: List<SpellLevel>,
    modifier: Modifier = Modifier,
    onEvent: (SpellEvent) -> Unit,
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
                onEvent = onEvent,
                mode = mode
            )
        }
    }
}