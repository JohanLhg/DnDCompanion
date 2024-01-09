package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent

@Composable
fun SpellCard(
    spell: SpellInfo,
    onEvent: (SpellEvent) -> Unit,
    mode: SpellListMode,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = {
                    onEvent(SpellEvent.OnSpellClicked(spell))
                }
            )
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = spell.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = if (spell.state == SpellState.HIGHLIGHTED)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small)
                        .padding(horizontal = MaterialTheme.spacing.extraSmall)
                )
                if (spell.components.contains('V')) {
                    ComponentImage(
                        painter = painterResource(id = R.drawable.vocal),
                        contentDescription = stringResource(id = R.string.vocal_component)
                    )
                }
                if (spell.components.contains('S')) {
                    ComponentImage(
                        painter = painterResource(id = R.drawable.somatic),
                        contentDescription = stringResource(id = R.string.somatic_component)
                    )
                }
                if (spell.components.contains('M')) {
                    ComponentImage(
                        painter = painterResource(id = R.drawable.materials),
                        contentDescription = stringResource(id = R.string.material_component)
                    )
                }
            }
            if (mode is SpellListMode.All && !spell.state.isUnlocked()) {
                Image(
                    painter = painterResource(
                        id = if (spell.state == SpellState.HIGHLIGHTED) R.drawable.eraser
                        else R.drawable.highlight
                    ),
                    contentDescription = stringResource(id = R.string.highlighted_spells),
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false)
                        ) {
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
        Column (
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraSmall)
        ) {
            Row {
                Text(
                    text = stringResource(id = R.string.spell_casting_time),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.small)
                )
                Text(
                    text = spell.castingTime,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.extraSmall),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Row {
                Text(
                    text = stringResource(id = R.string.spell_range),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.small)
                )
                Text(
                    text = spell.range,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.extraSmall),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Row {
                Text(
                    text = stringResource(id = R.string.spell_duration),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.small)
                )
                Text(
                    text = spell.duration,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.extraSmall),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Text(
                text = stringResource(id = R.string.spell_description),
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
            )
            Text(
                text = spell.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small),
                overflow = TextOverflow.Ellipsis,
                maxLines = 4
            )
        }
    }
}

@Composable
fun ComponentImage(
    painter: Painter,
    contentDescription: String
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(28.dp)
            .padding(MaterialTheme.spacing.extraSmall)
    )
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellCardPreview() {
    DnDCompanionTheme {
        SpellCard(
            spell = SpellInfo(
                cid = 1,
                level = 0,
                name = "Fireball",
                castingTime = "1 action",
                range = "150 feet",
                components = "V, S",
                duration = "Instantaneous",
                description = "A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere centered on that point must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one.\n" +
                        "\n" +
                        "The fire spreads around corners. It ignites flammable objects in the area that aren't being worn or carried.",
                higherLevels = "When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.",
                state = SpellState.HIGHLIGHTED
            ),
            onEvent = {},
            mode = SpellListMode.All(selectedLevel = 0),
            modifier = Modifier
                .width(300.dp)
        )
    }
}