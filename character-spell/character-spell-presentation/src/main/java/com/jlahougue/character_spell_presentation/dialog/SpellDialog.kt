package com.jlahougue.character_spell_presentation.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.character_spell_domain.model.SpellInfo
import com.jlahougue.character_spell_domain.model.SpellState
import com.jlahougue.character_spell_presentation.R
import com.jlahougue.character_spell_presentation.dialog.component.SpellDialogHeader
import com.jlahougue.class_domain.model.Class
import com.jlahougue.core_presentation.components.ListOfLinkedItems
import com.jlahougue.core_presentation.components.PropertyColumn
import com.jlahougue.core_presentation.components.PropertyRow
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.damage_type_domain.model.DamageType

@Composable
fun SpellDialog(
    state: SpellDialogState,
    onEvent: (SpellDialogEvent) -> Unit
) {
    val spell = state.spell ?: return
    if (!state.isShown) return
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
            SpellDialogHeader(
                state = state,
                onEvent = onEvent
            )
            HorizontalDivider(
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
                PropertyRow(
                    label = stringResource(id = R.string.spell_materials),
                    value = spell.materials
                )
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
                PropertyColumn(
                    label = stringResource(id = R.string.spell_higher_levels),
                    value = spell.higherLevels
                )
                ListOfLinkedItems(
                    title = stringResource(id = R.string.spell_damage_types),
                    items = spell.damageTypes,
                    itemToString = { it.name },
                    onEvent = { onEvent(SpellDialogEvent.OnDamageTypeClick(it)) }
                )
                ListOfLinkedItems(
                    title = stringResource(id = R.string.spell_classes),
                    items = spell.classes,
                    itemToString = { it.name },
                    onEvent = { onEvent(SpellDialogEvent.OnClassClick(it)) }
                )
            }
        }
    }
}

@Preview(
    apiLevel = 33,
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