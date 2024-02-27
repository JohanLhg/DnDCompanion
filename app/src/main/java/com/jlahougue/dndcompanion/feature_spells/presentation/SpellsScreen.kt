package com.jlahougue.dndcompanion.feature_spells.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.character_spell_domain.model.CharacterSpellsStatsView
import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.character_spell_domain.model.SpellLevel
import com.jlahougue.character_spell_domain.model.SpellSlotView
import com.jlahougue.character_spell_domain.model.SpellState
import com.jlahougue.character_spell_domain.model.SpellcasterView
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellLevelList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialog
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogState
import com.jlahougue.dndcompanion.feature_spells.presentation.components.FilteredSpellList
import com.jlahougue.dndcompanion.feature_spells.presentation.components.SpellStats
import com.jlahougue.dndcompanion.feature_spells.presentation.components.SpellcastingStats

@Composable
fun SpellsScreen(
    spellcasting: SpellcasterView,
    spellsStats: CharacterSpellsStatsView,
    search: String,
    classes: List<String>,
    selectedClass: String,
    spellLevels: List<Int>,
    selectedLevel: Int,
    allSpells: List<SpellInfo>,
    knownSpells: List<SpellLevel>,
    mode: SpellListMode,
    onSearchEvent: (SpellSearchEvent) -> Unit,
    onSpellEvent: (SpellEvent) -> Unit,
    dialogState: SpellDialogState,
    onDialogEvent: (SpellDialogEvent) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(IntrinsicSize.Max)
        ) {
            SpellcastingStats(
                spellcasting = spellcasting,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .width(125.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            SpellStats(
                stats = spellsStats,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Button(
                onClick = { onSearchEvent(SpellSearchEvent.OnModeChanged) },
                shape = OutlinedTextFieldDefaults.shape,
                contentPadding = PaddingValues(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = MaterialTheme.spacing.small
                ),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .fillMaxWidth()
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        imageVector = if (mode is SpellListMode.All) Icons.Filled.Done
                        else Icons.Filled.Edit,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(
                            id = if (mode is SpellListMode.All) R.string.done
                            else R.string.edit
                        ).uppercase(),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
        VerticalDivider()
        if (mode is SpellListMode.All) {
            FilteredSpellList(
                search = search,
                classes = classes,
                selectedClass = selectedClass,
                levels = spellLevels,
                selectedLevel = selectedLevel,
                spells = allSpells,
                onSearchEvent = onSearchEvent,
                onSpellEvent = onSpellEvent,
                mode = mode,
                modifier = Modifier
                    .fillMaxHeight()
            )
        } else {
            SpellLevelList(
                spells = knownSpells,
                onEvent = onSpellEvent,
                mode = mode,
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
    }
    SpellDialog(
        state = dialogState,
        onEvent = onDialogEvent
    )
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellsScreenPreview() {
    DnDCompanionTheme {
        SpellsScreen(
            spellcasting = SpellcasterView(
                ability = AbilityName.CONSTITUTION
            ),
            spellsStats = CharacterSpellsStatsView(
                totalHighlighted = 2,
                totalUnlocked = 10,
                totalPrepared = 4,
                maxPrepared = 5
            ),
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
            spellLevels = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
            selectedLevel = 0,
            allSpells = listOf(
                SpellInfo(
                    cid = 1,
                    level = 0,
                    id = "acid-splash",
                    name = "Acid Splash",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 2,
                    level = 0,
                    id = "blade-ward",
                    name = "Blade Ward",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 3,
                    level = 0,
                    id = "chill-touch",
                    name = "Chill Touch",
                    state = SpellState.HIGHLIGHTED
                ),
                SpellInfo(
                    cid = 4,
                    level = 0,
                    id = "dancing-lights",
                    name = "Dancing Lights",
                    state = SpellState.LOCKED
                ),
                SpellInfo(
                    cid = 5,
                    level = 0,
                    id = "fire-bolt",
                    name = "Fire Bolt",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 6,
                    level = 1,
                    id = "burning-hands",
                    name = "Burning Hands",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 7,
                    level = 1,
                    id = "charm-person",
                    name = "Charm Person",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 8,
                    level = 1,
                    id = "color-spray",
                    name = "Color Spray",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 9,
                    level = 1,
                    id = "comprehend-languages",
                    name = "Comprehend Languages",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 10,
                    level = 1,
                    id = "detect-magic",
                    name = "Detect Magic",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 11,
                    level = 1,
                    id = "disguise-self",
                    name = "Disguise Self",
                    state = SpellState.PREPARED
                ),
                SpellInfo(
                    cid = 12,
                    level = 1,
                    id = "expeditious-retreat",
                    name = "Expeditious Retreat",
                    state = SpellState.PREPARED
                )
            ),
            knownSpells = listOf(
                SpellLevel(
                    spellSlot = SpellSlotView(
                        cid = 1,
                        level = 0,
                        total = 0,
                        left = 0
                    ),
                    spells = listOf(
                        SpellInfo(
                            cid = 1,
                            id = "mage-hand",
                            name = "Mage Hand",
                            state = SpellState.PREPARED
                        ),
                    )
                ),
                SpellLevel(
                    spellSlot = SpellSlotView(
                        cid = 1,
                        level = 1,
                        total = 4,
                        left = 3
                    ),
                    spells = listOf(
                        SpellInfo(
                            cid = 1,
                            level = 1,
                            id = "acid-splash",
                            name = "Acid Splash",
                            state = SpellState.PREPARED
                        ),
                        SpellInfo(
                            cid = 2,
                            level = 1,
                            id = "blade-ward",
                            name = "Blade Ward",
                            state = SpellState.PREPARED
                        ),
                        SpellInfo(
                            cid = 3,
                            level = 1,
                            id = "chill-touch",
                            name = "Chill Touch",
                            state = SpellState.HIGHLIGHTED
                        ),
                        SpellInfo(
                            cid = 4,
                            level = 1,
                            id = "dancing-lights",
                            name = "Dancing Lights",
                            state = SpellState.LOCKED
                        ),
                        SpellInfo(
                            cid = 5,
                            level = 1,
                            id = "fire-bolt",
                            name = "Fire Bolt",
                            state = SpellState.PREPARED
                        ),
                    )
                )
            ),
            mode = SpellListMode.All(selectedLevel = 0),
            onSearchEvent = {},
            onSpellEvent = {},
            dialogState = SpellDialogState(
                isShown = true,
                spell = SpellInfo(
                    name = "Acid Splash",
                )
            ),
            onDialogEvent = {}
        )
    }
}