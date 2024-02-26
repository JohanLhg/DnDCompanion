package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent

@Composable
fun Spell(
    spell: SpellInfo,
    onEvent: (SpellEvent) -> Unit,
    mode: SpellListMode,
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
                .padding(horizontal = MaterialTheme.spacing.small)
        ) {
            when (mode) {
                is SpellListMode.Known -> {
                    if (spell.level != 0) {
                        Checkbox(
                            checked = spell.state.isPrepared(),
                            modifier = Modifier
                                .size(28.dp),
                            onCheckedChange = {
                                onEvent(
                                    SpellEvent.OnSpellStateChanged(
                                        spell,
                                        if (it) SpellState.PREPARED
                                        else SpellState.UNLOCKED
                                    )
                                )
                            }
                        )
                    }
                }
                is SpellListMode.All -> {
                    Checkbox(
                        checked = spell.state.isUnlocked(),
                        modifier = Modifier
                            .size(28.dp),
                        onCheckedChange = {
                            val state = if (it) {
                                if (spell.level == 0)
                                    SpellState.PREPARED
                                else
                                    SpellState.UNLOCKED
                            } else {
                                SpellState.LOCKED
                            }
                            onEvent(
                                SpellEvent.OnSpellStateChanged(
                                    spell,
                                    state
                                )
                            )
                        }
                    )
                }
                else -> {}
            }
            Text(
                text = spell.name,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onEvent(SpellEvent.OnSpellClicked(spell.id))
                        }
                    )
                    .padding(vertical = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    .weight(1f)
            )
            if (mode is SpellListMode.All && !spell.state.isUnlocked()) {
                Image(
                    painter = painterResource(id = R.drawable.highlight),
                    contentDescription = stringResource(id = R.string.highlighted_spells),
                    modifier = Modifier
                        .clickable {
                            onEvent(
                                SpellEvent.OnSpellStateChanged(
                                    spell,
                                    if (spell.state == SpellState.HIGHLIGHTED) SpellState.LOCKED
                                    else SpellState.HIGHLIGHTED
                                )
                            )
                        }
                        .size(28.dp)
                        .padding(MaterialTheme.spacing.extraSmall)
                )
            }
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
                level = 0,
                name = "Fireball",
            ),
            onEvent = {},
            mode = SpellListMode.All(selectedLevel = 0),
            modifier = Modifier
                .width(200.dp)
        )
    }
}