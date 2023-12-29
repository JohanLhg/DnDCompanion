package com.jlahougue.dndcompanion.feature_spells.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpellsStatsView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellcasterView
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.feature_spells.presentation.components.SpellcastingStats
import com.jlahougue.dndcompanion.feature_spells.presentation.components.SpellsStats

@Composable
fun SpellsScreen(
    spellcasting: SpellcasterView,
    spellsStats: CharacterSpellsStatsView,
    spellLevels: List<SpellLevel>,
    mode: SpellListMode
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            SpellcastingStats(
                spellcasting = spellcasting,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .width(125.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            SpellsStats(
                stats = spellsStats,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        SpellList(
            spells = spellLevels,
            mode = mode
        )
    }
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
            spellLevels = listOf(
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
                            id = "acid-splash",
                            name = "Acid Splash",
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
            mode = SpellListMode.Known { _, _ -> }
        )
    }
}