package com.jlahougue.dndcompanion.feature_spells.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo

@Composable
fun FilteredSpellList(
    search: String,
    classes: List<String>,
    spells: List<SpellInfo>,
    modifier: Modifier = Modifier
) {
}

@Preview
@Composable
fun FilteredSpellListPreview() {
    DnDCompanionTheme {
        FilteredSpellList(
            search = "",
            classes = listOf(),
            spells = listOf()
        )
    }
}