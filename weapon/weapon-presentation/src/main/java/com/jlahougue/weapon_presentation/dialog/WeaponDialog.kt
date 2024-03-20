package com.jlahougue.weapon_presentation.dialog

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_presentation.asShortUiText
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.ListOfLinkedItems
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.components.labeled_values.PropertyColumn
import com.jlahougue.core_presentation.components.labeled_values.PropertyRow
import com.jlahougue.core_presentation.components.text_fileds.Counter
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.weapon_domain.model.WeaponInfo
import com.jlahougue.weapon_presentation.R
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun WeaponDialog(
    state: WeaponDialogState,
    onEvent: (WeaponDialogEvent) -> Unit
) {
    val weapon = state.weapon ?: return
    val unitSystem = state.unitSystem
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(WeaponDialogEvent.OnDismiss) },
        modifier = Modifier.width(IntrinsicSize.Max),
        properties = DialogProperties(usePlatformDefaultWidth = false),
        header = {
            WeaponDialogHeader(
                weapon = weapon,
                unitSystem = unitSystem,
                onEvent = onEvent
            )
        }
    ) {
        if (weapon.test != AbilityName.NONE) {
            PropertyRow(
                label = stringResource(id = R.string.weapon_test),
                value = stringResource(
                    R.string.weapon_test_value,
                    weapon.modifier.toSignedString(),
                    weapon.test.asShortUiText().getString()
                )
            )
        }
        PropertyRow(
            label = stringResource(id = R.string.weapon_damage),
            value = if (weapon.damageType.isNotBlank()) weapon.damage + " " + weapon.damageType
            else weapon.damage
        )
        PropertyRow(
            label = stringResource(id = R.string.weapon_damage_special),
            value = if (weapon.twoHandedDamageType.isNotBlank())
                weapon.twoHandedDamage + " " + weapon.twoHandedDamageType
            else weapon.twoHandedDamage
        )
        PropertyColumn(
            label = stringResource(id = R.string.weapon_description),
            value = weapon.description
        )
        ListOfLinkedItems(
            title = stringResource(id = R.string.weapon_properties),
            items = weapon.properties,
            itemToString = { it.name },
            onEvent = { onEvent(WeaponDialogEvent.OnPropertyClick(it)) }
        )
    }
}

@Composable
fun RowScope.WeaponDialogHeader(
    weapon: WeaponInfo,
    unitSystem: UnitSystem,
    onEvent: (WeaponDialogEvent) -> Unit
) {
    Text(
        text = weapon.name,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary
    )
    Spacer(
        modifier = Modifier
            .width(MaterialTheme.spacing.extraSmall)
    )
    if (weapon.getRangeString(unitSystem).isNotBlank()) {
        Text(
            text = stringResource(
                id = CoreR.string.parenthesis,
                weapon.getRangeString(unitSystem)
            ),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
    Spacer(
        modifier = Modifier
            .sizeIn(minWidth = MaterialTheme.spacing.small)
            .weight(1f)
    )
    Counter(
        quantity = weapon.count,
        onQuantityChanged = { onEvent(WeaponDialogEvent.OnCountChange(weapon, it)) },
        modifier = Modifier.width(100.dp)
    )
}

@Preview
@Composable
fun WeaponDialogPreview() {
    DnDCompanionTheme {
        WeaponDialog(
            WeaponDialogState(
                isShown = true,
                weapon = WeaponInfo(
                    cid = 1,
                    name = "Dague",
                    count = 50,
                    test = AbilityName.INTELLIGENCE,
                    modifier = 2,
                    damage = "1d4",
                    damageType = "Perforant",
                    twoHandedDamage = "1d6",
                    twoHandedDamageType = "Perforant",
                    range = 20,
                    throwRangeMin = 60,
                    throwRangeMax = 200,
                ),
                unitSystem = UnitSystem.METRIC
            ),
            onEvent = {},
        )
    }
}