package com.jlahougue.dndcompanion.data_weapon.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.PropertyColumn
import com.jlahougue.dndcompanion.core.presentation.components.PropertyRow
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent

@Composable
fun WeaponCard(
    weapon: WeaponInfo,
    unitSystem: UnitSystem,
    onEvent: (WeaponEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = {
                    onEvent(WeaponEvent.OnWeaponClicked(weapon.name))
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small)
        ) {
            Text(
                text = weapon.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
            )
            if (weapon.getRangeString(unitSystem).isNotBlank()) {
                Text(
                    text = stringResource(
                        id = R.string.parenthesis,
                        weapon.getRangeString(unitSystem)
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "x" + weapon.count,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
            )
        }
        Divider(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )
        Column (
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
        ) {
            if (weapon.test != AbilityName.NONE) {
                PropertyRow(
                    label = stringResource(id = R.string.weapon_test),
                    value = stringResource(
                        R.string.weapon_test_value,
                        weapon.modifier.toSignedString(),
                        weapon.test.getShortString()
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
            unitSystem = UnitSystem.METRIC,
            onEvent = {},
        )
    }
}