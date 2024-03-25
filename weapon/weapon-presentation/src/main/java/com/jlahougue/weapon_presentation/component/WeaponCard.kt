package com.jlahougue.weapon_presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_presentation.asShortUiText
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.containers.DetailCard
import com.jlahougue.core_presentation.components.labeled_values.PropertyColumn
import com.jlahougue.core_presentation.components.labeled_values.PropertyRow
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.weapon_domain.model.WeaponInfo
import com.jlahougue.weapon_presentation.R
import com.jlahougue.weapon_presentation.WeaponEvent
import com.jlahougue.weapon_presentation.util.getRangeString
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun WeaponCard(
    weapon: WeaponInfo,
    unitSystem: com.jlahougue.user_info_domain.model.UnitSystem,
    onEvent: (WeaponEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    DetailCard(
        onClick = {
            onEvent(WeaponEvent.OnWeaponClicked(weapon.name))
        },
        modifier = modifier,
        headerAlignment = Alignment.Bottom,
        header = {
            WeaponCardHeader(
                weapon = weapon,
                unitSystem = unitSystem
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
            value = weapon.description,
            maxLines = 3
        )
    }
}

@Composable
fun WeaponCardHeader(
    weapon: WeaponInfo,
    unitSystem: com.jlahougue.user_info_domain.model.UnitSystem
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(vertical = MaterialTheme.spacing.small)
    ) {
        Text(
            text = weapon.name,
            style = MaterialTheme.typography.titleMedium
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
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(
            modifier = Modifier
                .sizeIn(minWidth = MaterialTheme.spacing.small)
            .weight(1f)
        )
        Text(
            text = "x" + weapon.count,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun WeaponCardPreview() {
    DnDCompanionTheme {
        WeaponCard(
            weapon = WeaponInfo(
                cid = 1,
                name = "Dague",
                count = 1,
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
            unitSystem = com.jlahougue.user_info_domain.model.UnitSystem.METRIC,
            onEvent = {},
        )
    }
}