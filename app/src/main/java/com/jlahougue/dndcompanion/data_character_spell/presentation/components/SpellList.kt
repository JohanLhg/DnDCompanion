package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo

@Composable
fun SpellList(
    spells: List<SpellInfo>,
    mode: SpellListMode,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp),
        modifier = modifier
    ) {
        items(
            items = spells,
            key = { it.id }
        ) {
            Spell(
                spell = it,
                mode = mode
            )
        }
    }
}