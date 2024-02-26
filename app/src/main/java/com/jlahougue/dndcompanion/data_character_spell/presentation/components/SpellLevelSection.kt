package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent

@Composable
fun SpellLevelSection(
    spellLevel: SpellLevel,
    onEvent: (SpellEvent) -> Unit,
    mode: SpellListMode,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        if (spellLevel.spellSlot.level == 0) {
            CantripsBanner(
                modifier = Modifier
                    .width(300.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        } else {
            SpellLevelBanner(
                spellSlot = spellLevel.spellSlot,
                onEvent = {
                    Log.d("SpellLevelSection", "SpellLevelBanner event")
                    onEvent(it)
                          },
                modifier = Modifier
                    .width(300.dp)
                    .padding(MaterialTheme.spacing.small)
            )
        }
        SpellList(
            spells = spellLevel.spells,
            onEvent = onEvent,
            mode = mode,
            modifier = Modifier
                .heightIn(max = 9999.dp)
                .padding(horizontal = MaterialTheme.spacing.small)
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellLevelPreview() {
    DnDCompanionTheme {
        SpellLevelSection(
            spellLevel = SpellLevel(
                spellSlot = SpellSlotView(
                    cid = 1,
                    level = 1,
                    total = 4,
                    left = 3
                ),
                spells = listOf(
                    SpellInfo(
                        cid = 1,
                        id = "acid-splash",
                        name = "Acid Splash",
                        state = SpellState.PREPARED
                    ),
                    SpellInfo(
                        cid = 2,
                        id = "blade-ward",
                        name = "Blade Ward",
                        state = SpellState.PREPARED
                    ),
                    SpellInfo(
                        cid = 3,
                        id = "chill-touch",
                        name = "Chill Touch",
                        state = SpellState.PREPARED
                    ),
                    SpellInfo(
                        cid = 4,
                        id = "dancing-lights",
                        name = "Dancing Lights",
                        state = SpellState.PREPARED
                    ),
                    SpellInfo(
                        cid = 5,
                        id = "fire-bolt",
                        name = "Fire Bolt",
                        state = SpellState.PREPARED
                    ),
                )
            ),
            onEvent = {},
            mode = SpellListMode.Prepared,
            modifier = Modifier
                .width(500.dp)
                .padding(MaterialTheme.spacing.small)
        )
    }
}