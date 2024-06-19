package com.jlahougue.spells_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.jlahougue.character_spell_presentation.SpellLevelList
import com.jlahougue.character_spell_presentation.components.SpellListMode
import com.jlahougue.character_spell_presentation.dialog.SpellDialog
import com.jlahougue.core_presentation.R
import com.jlahougue.core_presentation.components.MenuButton
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.damage_type_presentation.DamageTypeDialog
import com.jlahougue.spells_presentation.components.FilteredSpellList
import com.jlahougue.spells_presentation.components.SpellSearchEvent
import com.jlahougue.spells_presentation.components.SpellSearchState
import com.jlahougue.spells_presentation.components.SpellStats
import com.jlahougue.spells_presentation.components.SpellcastingStats
import com.jlahougue.spells_presentation.components.source_selection.SourceSelectionDialog
import com.jlahougue.spells_presentation.components.source_selection.SourceSelectionEvent

@Composable
fun SpellsScreen(
    state: SpellsState,
    onEvent: (SpellsEvent) -> Unit
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
                spellcasting = state.spellcasting,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .width(125.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            SpellStats(
                stats = state.spellsStats,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            MenuButton(
                label = stringResource(id = R.string.sources),
                icon = Icons.Filled.Settings,
                onClick = {
                    onEvent(SpellsEvent.OnSourceSelectionEvent(SourceSelectionEvent.OnShow))
                },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            MenuButton(
                label = stringResource(
                    id = if (state.mode is SpellListMode.All) R.string.done
                    else R.string.edit
                ),
                icon = if (state.mode is SpellListMode.All) Icons.Filled.Done
                else Icons.Filled.Edit,
                onClick = {
                    onEvent(SpellsEvent.OnSearchEvent(SpellSearchEvent.OnModeChanged))
                },
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
        VerticalDivider()
        if (state.mode is SpellListMode.All) {
            FilteredSpellList(
                searchState = state.search,
                classes = state.classes,
                levels = state.spellLevels,
                spells = state.allSpells,
                onSearchEvent = {
                    onEvent(SpellsEvent.OnSearchEvent(it))
                },
                onSpellEvent = {
                    onEvent(SpellsEvent.OnSpellEvent(it))
                },
                mode = state.mode,
                modifier = Modifier.fillMaxHeight()
            )
        } else {
            SpellLevelList(
                spells = state.knownSpells,
                onEvent = {
                    onEvent(SpellsEvent.OnSpellEvent(it))
                },
                mode = state.mode,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
    SpellDialog(
        state = state.spellDialog,
        onEvent = {
            onEvent(SpellsEvent.OnDialogEvent(it))
        }
    )
    DamageTypeDialog(
        state = state.damageTypeDialog,
        onEvent = {
            onEvent(SpellsEvent.OnDamageTypeDialogEvent(it))
        }
    )
    SourceSelectionDialog(
        state = state.sourceSelection,
        onEvent = {
            onEvent(SpellsEvent.OnSourceSelectionEvent(it))
        }
    )
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellsScreenPreview() {
    DnDCompanionTheme {
        SpellsScreen(
            SpellsState(
                spellcasting = SpellcasterView(
                    ability = AbilityName.CONSTITUTION
                ),
                spellsStats = CharacterSpellsStatsView(
                    totalHighlighted = 2,
                    totalUnlocked = 10,
                    totalPrepared = 4,
                    maxPrepared = 5
                ),
                search = SpellSearchState(
                    search = "chill",
                    selectedClass = "Wizard",
                    selectedLevel = 0
                ),
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
                spellLevels = listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
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
                mode = SpellListMode.All(selectedLevel = 0)
            ),
            onEvent = {}
        )
    }
}