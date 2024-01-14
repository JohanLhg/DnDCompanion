package com.jlahougue.dndcompanion.data_weapon.presentation.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.extension.toSignedString
import com.jlahougue.dndcompanion.core.presentation.components.ListOfLinkedItems
import com.jlahougue.dndcompanion.core.presentation.components.PropertyColumn
import com.jlahougue.dndcompanion.core.presentation.components.PropertyRow
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo

@Composable
fun WeaponDialog(
    state: WeaponDialogState,
    onEvent: (WeaponDialogEvent) -> Unit
) {
    val weapon = state.weapon ?: return
    val unitSystem = state.unitSystem
    if (state.isShown) {
        Dialog(
            onDismissRequest = {
                onEvent(WeaponDialogEvent.OnDismiss)
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
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.small)
                    ) {
                        Text(
                            text = stringResource(R.string.amount, weapon.count, weapon.name),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(vertical = MaterialTheme.spacing.small)
                                .padding(horizontal = MaterialTheme.spacing.extraSmall)
                        )
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
                    Divider(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                    )
                    Column(
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
                    ) {
                        PropertyRow(
                            label = stringResource(id = R.string.weapon_test),
                            value = stringResource(
                                R.string.weapon_test_value,
                                weapon.modifier.toSignedString(),
                                weapon.test.getShortString()
                            )
                        )
                        PropertyRow(
                            label = stringResource(id = R.string.weapon_damage),
                            value = if (weapon.damageType != null) weapon.damage + " " + weapon.damageType!!.name
                            else weapon.damage
                        )
                        PropertyRow(
                            label = stringResource(id = R.string.weapon_damage_special),
                            value = if (weapon.twoHandedDamageType != null)
                                weapon.twoHandedDamage + " " + weapon.twoHandedDamageType!!.name
                            else weapon.twoHandedDamage
                        )
                        PropertyColumn(
                            label = stringResource(id = R.string.weapon_description),
                            value = weapon.description,
                            maxLines = 3
                        )
                        ListOfLinkedItems(
                            title = stringResource(id = R.string.weapon_properties),
                            items = weapon.properties,
                            itemToString = { it.name },
                            onEvent = { onEvent(WeaponDialogEvent.OnPropertyClick(it)) }
                        )
                    }
                }
            }
        }
    }
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
                    count = 1,
                    test = AbilityName.INTELLIGENCE,
                    modifier = 2,
                    damage = "1d4",
                    damageType = DamageType(
                        name = "Perforant",
                        description = "Perforant"
                    ),
                    twoHandedDamage = "1d6",
                    twoHandedDamageType = DamageType(
                        name = "Perforant",
                        description = "Perforant"
                    ),
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