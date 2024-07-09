package com.jlahougue.class_presentation.list_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_presentation.R
import com.jlahougue.core_presentation.components.CancelButton
import com.jlahougue.core_presentation.components.ConfirmButton
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun ClassListDialog(
    state: ClassListDialogState,
    onEvent: (ClassListDialogEvent) -> Unit
) {
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(ClassListDialogEvent.OnDismiss) },
        title = stringResource(id = R.string.class_list_dialog_title),
        hasContentPadding = false
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(state.classes) { index, clazz ->
                ClassItem(
                    clazz = clazz,
                    selected = state.selectedClass == clazz,
                    backgroundColor = if (index % 2 == 0) MaterialTheme.colorScheme.surface
                    else MaterialTheme.colorScheme.surfaceVariant,
                    onEvent = onEvent
                )
            }
        }
        HorizontalDivider()
        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            CancelButton(
                onClick = { onEvent(ClassListDialogEvent.OnDismiss) }
            )
            ConfirmButton(
                onClick = { onEvent(ClassListDialogEvent.OnConfirm) },
                enabled = state.selectedClass != null
            )
        }
    }
}

@Composable
fun ClassItem(
    clazz: Class,
    selected: Boolean,
    backgroundColor: Color,
    onEvent: (ClassListDialogEvent) -> Unit
) {
    val color = if (selected) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onSurface
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable {
                onEvent(ClassListDialogEvent.OnClassSelected(clazz))
            }
            .background(
                if (selected) MaterialTheme.colorScheme.primary
                else backgroundColor
            )
            .padding(MaterialTheme.spacing.small)
            .fillMaxWidth()
    ) {
        Text(
            text = clazz.name,
            style = MaterialTheme.typography.titleSmall,
            color = color
        )
        Spacer(modifier = Modifier.weight(1f))
        if (clazz.isSpellcaster()) {
            Image(
                painter = painterResource(id = CoreR.drawable.spell_book),
                contentDescription = stringResource(id = R.string.spellcaster),
                modifier = Modifier.size(35.dp)
                    .padding(MaterialTheme.spacing.extraSmall),
                colorFilter = ColorFilter.tint(color)
            )
        }
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = stringResource(id = R.string.more_info),
            modifier = Modifier
                .size(35.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(bounded = false),
                    onClick = {
                        onEvent(ClassListDialogEvent.OnClassDetailsOpened(clazz))
                    }
                )
                .padding(MaterialTheme.spacing.extraSmall),
            tint = color
        )
    }
}

@Preview(
    apiLevel = 33,
    showBackground = false,
    device = Devices.TABLET
)
@Composable
private fun ClassListDialogPreview() {
    DnDCompanionTheme {
        ClassListDialog(
            state = ClassListDialogState(
                isShown = true,
                selectedClass = Class(
                    name = "Wizard",
                    hitDice = 8,
                    equipment = "A rapier, a longsword, a diplomat's pack, and a lute",
                    profSavingThrows = "Dexterity, Charisma",
                    profSkills = "Any three",
                    profArmor = "Light armor",
                    profWeapons = "Simple weapons, hand crossbows, longswords, rapiers, shortswords",
                    profTools = "Three musical instruments",
                    spellcastingAbility = AbilityName.INTELLIGENCE
                ),
                classes = listOf(
                    Class(
                        name = "Barbarian",
                        hitDice = 12,
                        equipment = "A greataxe, two handaxes, an explorer's pack, and four javelins",
                        profSavingThrows = "Strength, Constitution",
                        profSkills = "Animal Handling, Athletics, Intimidation, Nature, Perception, Survival",
                        profArmor = "Light armor, medium armor, shields",
                        profWeapons = "Simple weapons, martial weapons",
                        profTools = "None",
                        spellcastingAbility = AbilityName.NONE
                    ),
                    Class(
                        name = "Bard",
                        hitDice = 8,
                        equipment = "A rapier, a longsword, a diplomat's pack, and a lute",
                        profSavingThrows = "Dexterity, Charisma",
                        profSkills = "Any three",
                        profArmor = "Light armor",
                        profWeapons = "Simple weapons, hand crossbows, longswords, rapiers, shortswords",
                        profTools = "Three musical instruments",
                        spellcastingAbility = AbilityName.CHARISMA
                    ),
                    Class(
                        name = "Barbarian",
                        hitDice = 12,
                        equipment = "A greataxe, two handaxes, an explorer's pack, and four javelins",
                        profSavingThrows = "Strength, Constitution",
                        profSkills = "Animal Handling, Athletics, Intimidation, Nature, Perception, Survival",
                        profArmor = "Light armor, medium armor, shields",
                        profWeapons = "Simple weapons, martial weapons",
                        profTools = "None",
                        spellcastingAbility = AbilityName.NONE
                    ),
                    Class(
                        name = "Bard",
                        hitDice = 8,
                        equipment = "A rapier, a longsword, a diplomat's pack, and a lute",
                        profSavingThrows = "Dexterity, Charisma",
                        profSkills = "Any three",
                        profArmor = "Light armor",
                        profWeapons = "Simple weapons, hand crossbows, longswords, rapiers, shortswords",
                        profTools = "Three musical instruments",
                        spellcastingAbility = AbilityName.CHARISMA
                    ),
                    Class(
                        name = "Barbarian",
                        hitDice = 12,
                        equipment = "A greataxe, two handaxes, an explorer's pack, and four javelins",
                        profSavingThrows = "Strength, Constitution",
                        profSkills = "Animal Handling, Athletics, Intimidation, Nature, Perception, Survival",
                        profArmor = "Light armor, medium armor, shields",
                        profWeapons = "Simple weapons, martial weapons",
                        profTools = "None",
                        spellcastingAbility = AbilityName.NONE
                    ),
                    Class(
                        name = "Bard",
                        hitDice = 8,
                        equipment = "A rapier, a longsword, a diplomat's pack, and a lute",
                        profSavingThrows = "Dexterity, Charisma",
                        profSkills = "Any three",
                        profArmor = "Light armor",
                        profWeapons = "Simple weapons, hand crossbows, longswords, rapiers, shortswords",
                        profTools = "Three musical instruments",
                        spellcastingAbility = AbilityName.CHARISMA
                    ),
                    Class(
                        name = "Wizard",
                        hitDice = 8,
                        equipment = "A rapier, a longsword, a diplomat's pack, and a lute",
                        profSavingThrows = "Dexterity, Charisma",
                        profSkills = "Any three",
                        profArmor = "Light armor",
                        profWeapons = "Simple weapons, hand crossbows, longswords, rapiers, shortswords",
                        profTools = "Three musical instruments",
                        spellcastingAbility = AbilityName.INTELLIGENCE
                    ),
                    Class(
                        name = "Barbarian",
                        hitDice = 12,
                        equipment = "A greataxe, two handaxes, an explorer's pack, and four javelins",
                        profSavingThrows = "Strength, Constitution",
                        profSkills = "Animal Handling, Athletics, Intimidation, Nature, Perception, Survival",
                        profArmor = "Light armor, medium armor, shields",
                        profWeapons = "Simple weapons, martial weapons",
                        profTools = "None",
                        spellcastingAbility = AbilityName.NONE
                    ),
                    Class(
                        name = "Bard",
                        hitDice = 8,
                        equipment = "A rapier, a longsword, a diplomat's pack, and a lute",
                        profSavingThrows = "Dexterity, Charisma",
                        profSkills = "Any three",
                        profArmor = "Light armor",
                        profWeapons = "Simple weapons, hand crossbows, longswords, rapiers, shortswords",
                        profTools = "Three musical instruments",
                        spellcastingAbility = AbilityName.CHARISMA
                    ),
                )
            ),
            onEvent = { }
        )
    }
}