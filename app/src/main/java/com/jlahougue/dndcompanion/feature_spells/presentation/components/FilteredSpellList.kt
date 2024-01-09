package com.jlahougue.dndcompanion.feature_spells.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.CustomSearchBar
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.feature_spells.presentation.SpellSearchEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilteredSpellList(
    search: String,
    classes: List<String>,
    selectedClass: String,
    levels: List<Int>,
    selectedLevel: Int,
    spells: List<SpellInfo>,
    onSearchEvent: (SpellSearchEvent) -> Unit,
    onSpellEvent: (SpellEvent) -> Unit,
    mode: SpellListMode,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        var filterRowHeight by remember { mutableStateOf(0.dp) }
        val density = LocalDensity.current
        fun Int.asDp() = density.run {
            this@asDp.toDp()
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .onSizeChanged {
                     filterRowHeight = it.height.asDp()
                }
                .fillMaxWidth()
        ) {
            CustomSearchBar(
                value = search,
                onValueChange = {
                    onSearchEvent(SpellSearchEvent.OnSearchChanged(it))
                },
                modifier = Modifier
                    .width(200.dp)
            )
            Divider(
                modifier = Modifier
                    .height(filterRowHeight)
                    .width(1.dp)
            )
            LazyRow(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = classes,
                    key = { it }
                ) {
                    FilterChip(
                        selected = selectedClass == it,
                        label = {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodySmall
                            )
                        },
                        onClick = {
                            onSearchEvent(SpellSearchEvent.OnClassFilterClicked(it))
                        },
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }
            }
            Divider(
                modifier = Modifier
                    .height(filterRowHeight)
                    .width(1.dp)
            )
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = null,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .size(28.dp)
            )
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
                            text = if (it == 0) stringResource(id = R.string.cantrips)
                            else stringResource(id = R.string.spell_level, it),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    onClick = {
                        onSearchEvent(SpellSearchEvent.OnLevelSelected(it))
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
            onEvent = onSpellEvent,
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small)
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
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
            selectedClass = "Wizard",
            levels = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
            selectedLevel = 0,
            spells = listOf(),
            onSearchEvent = {},
            onSpellEvent = {},
            mode = SpellListMode.All(selectedLevel = 0)
        )
    }
}