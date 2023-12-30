package com.jlahougue.dndcompanion.feature_spells.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.CustomSearchBar
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.feature_spells.presentation.SpellEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredSpellList(
    search: String,
    classes: List<String>,
    selectedClasses: List<String>,
    levels: List<Int>,
    selectedLevel: Int,
    spells: List<SpellInfo>,
    onEvent: (SpellEvent) -> Unit,
    mode: SpellListMode,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomSearchBar(
                value = search,
                onValueChange = {
                    onEvent(SpellEvent.OnSearchChange(it))
                },
                modifier = Modifier
                    .width(200.dp)
            )
            LazyRow {
                items(
                    items = classes,
                    key = { it }
                ) {
                    FilterChip(
                        selected = selectedClasses.contains(it),
                        label = {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        onClick = {
                            onEvent(SpellEvent.OnClassFilterClick(it))
                        },
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }
            }
        }
        Divider()
        LazyRow {
            items(
                items = levels,
                key = { it }
            ) {
                FilterChip(
                    selected = selectedLevel == it,
                    label = {
                        Text(
                            text = if (it == 0) stringResource(
                                id = R.string.cantrips
                            )
                            else stringResource(
                                id = R.string.spell_level,
                                it
                            ),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onClick = {
                        onEvent(SpellEvent.OnLevelSelected(it))
                    },
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.extraSmall)
                )
            }
        }
        Divider()
        SpellList(
            spells = spells,
            mode = mode,
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small)
        )
    }
}

@Preview
@Composable
fun FilteredSpellListPreview() {
    DnDCompanionTheme {
        FilteredSpellList(
            search = "",
            classes = listOf(
                "Bard",
                "Cleric",
                "Druid",
                "Paladin",
                "Ranger",
                "Sorcerer",
                "Warlock",
                "Wizard"
            ),
            selectedClasses = listOf(
                "Wizard"
            ),
            levels = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
            selectedLevel = 0,
            spells = listOf(),
            onEvent = {},
            mode = SpellListMode.All(selectedLevel = 0) { _, _ -> }
        )
    }
}