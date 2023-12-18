package com.jlahougue.dndcompanion.data_character_spell.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState

@Composable
fun Spell(
    spell: SpellInfo,
    setSpellState: (SpellInfo, SpellState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Checkbox(
                checked = spell.state.isUnlocked(),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .size(20.dp),
                onCheckedChange = {
                    setSpellState(
                        spell,
                        if (it) SpellState.UNLOCKED
                        else SpellState.PREPARED
                    )
                }
            )
            Text(
                text = spell.name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(vertical =MaterialTheme.spacing.small)
                    .fillMaxWidth()
            )
        }
        Divider(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellPreview() {
    DnDCompanionTheme {
        Spell(
            spell = SpellInfo(
                cid = 1,
                name = "Fireball",
            ),
            setSpellState = { _, _ -> }
        )
    }
}