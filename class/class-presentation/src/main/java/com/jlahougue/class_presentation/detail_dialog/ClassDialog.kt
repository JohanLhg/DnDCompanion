package com.jlahougue.class_presentation.detail_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_presentation.asUiText
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_presentation.R
import com.jlahougue.class_presentation.components.ClassLevelArray
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.components.labeled_values.PropertyColumn
import com.jlahougue.core_presentation.components.labeled_values.PropertyRow
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun ClassDialog(
    state: ClassDialogState,
    onEvent: (ClassDialogEvent) -> Unit
) {
    val clazz = state.clazz ?: return
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(ClassDialogEvent.OnDismiss) },
        title = clazz.name
    ) {
        PropertyRow(
            label = stringResource(id = R.string.hit_die),
            value = stringResource(id = R.string.die, clazz.hitDice)
        )
        if (clazz.spellcastingAbility != AbilityName.NONE) {
            PropertyRow(
                label = stringResource(id = R.string.class_spellcasting_ability),
                value = clazz.spellcastingAbility.asUiText().getString()
            )
        }
        PropertyColumn(
            label = stringResource(id = R.string.equipment),
            value = clazz.equipment
        )
        Column(
            modifier = Modifier
                .padding(
                    horizontal = MaterialTheme.spacing.small,
                    vertical = MaterialTheme.spacing.extraSmall
                )
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
        ClassLevelArray(
            levels = state.levels
        )
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
