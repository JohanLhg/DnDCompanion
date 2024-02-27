package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent

@Composable
fun SpellList(
    spells: List<SpellInfo>,
    onEvent: (SpellEvent) -> Unit,
    mode: SpellListMode,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        modifier = modifier
    ) {
        items(
            items = spells,
            key = { it.id }
        ) {
            SpellCard(
                spell = it,
                onEvent = onEvent,
                mode = mode
            )
        }
    }
}