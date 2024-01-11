package com.jlahougue.dndcompanion.data_character_spell.presentation.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.PropertyColumn
import com.jlahougue.dndcompanion.core.presentation.components.PropertyRow
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellState
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.ComponentImage
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpellDialog(
    state: SpellDialogState,
    onEvent: (SpellDialogEvent) -> Unit
) {
    val spell = state.spell ?: return
    if (state.isShown) {
        Dialog(
            onDismissRequest = {
                onEvent(SpellDialogEvent.OnDismiss)
            }
        ) {
            Card(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = MaterialTheme.spacing.small)
                    ) {
                        Text(
                            text = spell.name,
                            style = MaterialTheme.typography.titleMedium,
                            color = if (spell.state == SpellState.HIGHLIGHTED)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier
                                .padding(vertical = MaterialTheme.spacing.medium)
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
                        if (spell.level > 0) {
                            Spacer(modifier = Modifier.weight(1f))
                            val context = LocalContext.current
                            ExposedDropdownMenuBox(
                                expanded = state.isStateDropdownOpened,
                                onExpandedChange = { onEvent(SpellDialogEvent.OnStateDropdownOpen(it)) },
                                modifier = Modifier
                                    .width(200.dp)
                            ) {
                                TextField(
                                    value = spell.state.label.getString(),
                                    onValueChange = {},
                                    textStyle = MaterialTheme.typography.bodySmall,
                                    readOnly = true,
                                    trailingIcon = {
                                        ExposedDropdownMenuDefaults.TrailingIcon(
                                            expanded = state.isStateDropdownOpened
                                        )
                                    },
                                    modifier = Modifier.menuAnchor()
                                )

                                ExposedDropdownMenu(
                                    expanded = state.isStateDropdownOpened,
                                    onDismissRequest = {
                                        onEvent(SpellDialogEvent.OnStateDropdownOpen(false))
                                    }
                                ) {
                                    SpellState.entries.forEach { state ->
                                        DropdownMenuItem(
                                            text = {
                                                Text(
                                                    text = state.label.getString(context),
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            },
                                            onClick = {
                                                onEvent(
                                                    SpellDialogEvent.OnStateChange(
                                                        spell,
                                                        state
                                                    )
                                                )
                                                onEvent(SpellDialogEvent.OnStateDropdownOpen(false))
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                    )
                    Column(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                            .padding(
                                top = MaterialTheme.spacing.extraSmall,
                                bottom = MaterialTheme.spacing.small
                            )
                    ) {
                        PropertyRow(
                            label = stringResource(id = R.string.spell_casting_time),
                            value = spell.castingTime
                        )
                        PropertyRow(
                            label = stringResource(id = R.string.spell_range),
                            value = spell.range
                        )
                        if (spell.materials.isNotEmpty()) {
                            PropertyRow(
                                label = stringResource(id = R.string.spell_materials),
                                value = spell.materials
                            )
                        }
                        PropertyRow(
                            label = stringResource(id = R.string.spell_duration),
                            value = if (spell.concentration)
                                stringResource(
                                    R.string.spell_concentration_duration,
                                    spell.duration
                                )
                            else spell.duration
                        )
                        if (spell.ritual) {
                            Text(
                                text = stringResource(id = R.string.spell_ritual),
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.spacing.small),
                            )
                        }
                        PropertyColumn(
                            label = stringResource(id = R.string.spell_description),
                            value = spell.description
                        )
                        if (spell.higherLevels.isNotEmpty()) {
                            PropertyColumn(
                                label = stringResource(id = R.string.spell_higher_levels),
                                value = spell.higherLevels
                            )
                        }
                        if (spell.damageTypes.isNotEmpty()) {
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.spacing.small)
                            ) {
                                Text(
                                    text = stringResource(id = R.string.spell_damage_types),
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier
                                        .padding(end = MaterialTheme.spacing.extraSmall)
                                )
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                                ) {
                                    itemsIndexed(
                                        items = spell.damageTypes,
                                        key = { _, damageType -> damageType.name }
                                    ) { index, damageType ->
                                        Text(
                                            text = damageType.name,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier
                                                .clickable {
                                                    onEvent(
                                                        SpellDialogEvent.OnDamageTypeClick(
                                                            damageType
                                                        )
                                                    )
                                                }
                                        )
                                        if (index < spell.damageTypes.size - 1) {
                                            Text(
                                                text = ",",
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Row(
                            modifier = Modifier
                                .padding(horizontal = MaterialTheme.spacing.small)
                        ) {
                            Text(
                                text = stringResource(id = R.string.spell_classes),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier
                                    .padding(end = MaterialTheme.spacing.extraSmall)
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
                            ) {
                                itemsIndexed(
                                    items = spell.classes,
                                    key = { _, clazz -> clazz.name }
                                ) { index, clazz ->
                                    Text(
                                        text = clazz.name,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .clickable {
                                                onEvent(SpellDialogEvent.OnClassClick(clazz))
                                            }
                                    )
                                    if (index < spell.classes.size - 1) {
                                        Text(
                                            text = ",",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SpellDialogPreview() {
    DnDCompanionTheme {
        SpellDialog(
            state = SpellDialogState(
                isShown = true,
                spell = SpellInfo(
                    name = "Fireball",
                    level = 3,
                    castingTime = "1 action",
                    range = "150 feet",
                    components = "V, S, M",
                    materials = "a tiny ball of bat guano and sulfur",
                    ritual = true,
                    concentration = true,
                    duration = "Instantaneous",
                    description = "A bright streak flashes from your pointing finger to a point you choose within range and then blossoms with a low roar into an explosion of flame. Each creature in a 20-foot-radius sphere centered on that point must make a Dexterity saving throw. A target takes 8d6 fire damage on a failed save, or half as much damage on a successful one.\n\nThe fire spreads around corners. It ignites flammable objects in the area that aren't being worn or carried.",
                    higherLevels = "When you cast this spell using a spell slot of 4th level or higher, the damage increases by 1d6 for each slot level above 3rd.",
                    state = SpellState.UNLOCKED,
                    classes = listOf(
                        Class(
                            name = "Sorcerer",
                            hitDice = 6,
                            equipment = "a light crossbow and 20 bolts or (a) any simple weapon (b) a component pouch or (b) an arcane focus (a) a dungeoneer's pack or (b) an explorer's pack",
                            profSavingThrows = "Constitution, Charisma",
                            profSkills = "Choose two from Arcana, Deception, Insight, Intimidation, Persuasion, and Religion",
                            profArmor = "None",
                            profWeapons = "Daggers, darts, slings, quarterstaffs, light crossbows",
                            profTools = "None",
                            spellcastingAbility = AbilityName.CHARISMA
                        ),
                        Class(
                            name = "Wizard",
                            hitDice = 6,
                            equipment = "(a) a quarterstaff or (b) a dagger (a) a component pouch or (b) an arcane focus (a) a scholar's pack or (b) an explorer's pack A spellbook",
                            profSavingThrows = "Intelligence, Wisdom",
                            profSkills = "Choose two from Arcana, History, Insight, Investigation, Medicine, and Religion",
                            profArmor = "None",
                            profWeapons = "Daggers, darts, slings, quarterstaffs, light crossbows",
                            profTools = "None",
                            spellcastingAbility = AbilityName.INTELLIGENCE
                        )
                    ),
                    damageTypes = listOf(
                        DamageType(
                            name = "Fire",
                            description = "Fire damage is one of the five damage types and is one of the most commonly resisted types of damage dealt. It is also one of the most commonly inflicted types of damage, especially by spells. Fire damage is dealt by most Hellfire weapons and by the spells fire bolt, fireball, and meteor swarm."
                        ),
                        DamageType(
                            name = "Electricity",
                            description = "Fire damage is one of the five damage types and is one of the most commonly resisted types of damage dealt. It is also one of the most commonly inflicted types of damage, especially by spells. Fire damage is dealt by most Hellfire weapons and by the spells fire bolt, fireball, and meteor swarm."
                        )
                    ),
                )
            ),
            onEvent = {}
        )
    }
}