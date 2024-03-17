package com.jlahougue.class_presentation

import androidx.compose.foundation.BorderStroke
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
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_presentation.components.ClassLevelArray
import com.jlahougue.core_presentation.components.PropertyRow
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun ClassDialog(
    state: ClassDialogState,
    onEvent: (ClassDialogEvent) -> Unit
) {
    if (!state.isShown) return
    val clazz = state.clazz ?: return

    Dialog(onDismissRequest = { onEvent(ClassDialogEvent.OnDismiss) }) {
        Card(
            //modifier = Modifier.width(IntrinsicSize.Max),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
            ) {
                Text(
                    text = clazz.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(
                            vertical = MaterialTheme.spacing.medium,
                            horizontal = MaterialTheme.spacing.extraSmall
                        )
                )
                HorizontalDivider()
                Column(
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small)
                ) {
                    PropertyRow(
                        label = stringResource(id = R.string.hit_die),
                        value = stringResource(id = R.string.die, clazz.hitDice)
                    )
                    if (clazz.spellcastingAbility != AbilityName.NONE) {
                        PropertyRow(
                            label = stringResource(id = R.string.spellcasting_ability),
                            value = clazz.spellcastingAbility.getString()
                        )
                    }
                    PropertyRow(
                        label = stringResource(id = R.string.equipment),
                        value = clazz.equipment
                    )
                    Column(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.small)
                            .padding(top = MaterialTheme.spacing.extraSmall)
                    ) {
                        Text(
                            text = stringResource(id = R.string.proficiencies),
                            style = MaterialTheme.typography.titleSmall,
                        )
                        PropertyRow(
                            label = stringResource(id = R.string.saving_throws),
                            value = clazz.profSavingThrows
                        )
                        PropertyRow(
                            label = stringResource(id = R.string.skills),
                            value = clazz.profSkills
                        )
                        if (clazz.profArmor.equals("None", ignoreCase = true).not()) {
                            PropertyRow(
                                label = stringResource(id = R.string.armor),
                                value = clazz.profArmor
                            )
                        }
                        PropertyRow(
                            label = stringResource(id = R.string.weapons),
                            value = clazz.profWeapons
                        )
                        if (clazz.profTools.equals("None", ignoreCase = true).not()) {
                            PropertyRow(
                                label = stringResource(id = R.string.tools),
                                value = clazz.profTools
                            )
                        }
                    }
                }
                ClassLevelArray()
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
private fun ClassDialogPreview() {
    DnDCompanionTheme {
        ClassDialog(
            state = ClassDialogState(
                isShown = true,
                clazz = Class(
                    name = "Barbarian",
                    hitDice = 12,
                    equipment = "A greataxe, a martial weapon, and two handaxes",
                    profSavingThrows = "Strength, Constitution",
                    profSkills = "Animal Handling, Athletics, Intimidation, Nature, Perception, Survival",
                    profArmor = "Light armor, medium armor, shields",
                    profWeapons = "Simple weapons, martial weapons",
                    profTools = "None",
                    spellcastingAbility = AbilityName.INTELLIGENCE
                )
            ),
            onEvent = {}
        )
    }
}
