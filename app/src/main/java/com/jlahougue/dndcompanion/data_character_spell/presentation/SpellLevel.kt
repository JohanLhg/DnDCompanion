package com.jlahougue.dndcompanion.data_character_spell.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState

@Composable
fun SpellLevel(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SpellLevelBanner(
            SpellSlotView(
                cid = 1,
                level = 2,
                total = 4,
                left = 3
            ),
            modifier = Modifier
                .width(300.dp)
                .padding(MaterialTheme.spacing.small)
        )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(200.dp),
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        ) {
            items(10) {
                Spell(
                    spell = SpellInfo(
                        cid = 1,
                        name = "Fireball",
                        state = SpellState.UNLOCKED,
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellLevelPreview() {
    DnDCompanionTheme {
        SpellLevel(
            modifier = Modifier.width(2560.dp)
        )
    }
}