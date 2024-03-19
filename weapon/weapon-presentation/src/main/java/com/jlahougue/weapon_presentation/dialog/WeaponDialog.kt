package com.jlahougue.weapon_presentation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.ListOfLinkedItems
import com.jlahougue.core_presentation.components.labeled_values.PropertyColumn
import com.jlahougue.core_presentation.components.labeled_values.PropertyRow
import com.jlahougue.core_presentation.components.text_fileds.CustomOutlinedTextField
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
                            text = weapon.name,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(vertical = MaterialTheme.spacing.small)
                                .padding(horizontal = MaterialTheme.spacing.extraSmall)
                        )
                        if (weapon.getRangeString(unitSystem).isNotBlank()) {
                            Text(
                                text = stringResource(
                                    id = CoreR.string.parenthesis,
                                    weapon.getRangeString(unitSystem)
                                ),
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier
                                    .padding(vertical = MaterialTheme.spacing.small)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start = MaterialTheme.spacing.small)
                                .sizeIn(maxWidth = 100.dp)
                        ) {
                            val focusManager = LocalFocusManager.current
                            Image(
                                painter = painterResource(id = CoreR.drawable.chevron_left),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight,
                                alpha = if (weapon.count <= 0) 0.2f else 1f,
                                modifier = Modifier
                                    .height(35.dp)
                                    .clickable(
                                        enabled = weapon.count > 0,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(bounded = false),
                                        onClick = {
                                            focusManager.clearFocus()
                                            onEvent(
                                                WeaponDialogEvent.OnCountChange(
                                                    weapon,
                                                    weapon.count - 1
                                                )
                                            )
                                        },
                                    )
                                    .padding(vertical = MaterialTheme.spacing.small)
                            )
                            CustomOutlinedTextField(
                                value = weapon.count.toString(),
                                onValueChange = {
                                    onEvent(
                                        WeaponDialogEvent.OnCountChange(
                                            weapon,
                                            it.toIntOrNull() ?: 0
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .padding(
                                        horizontal = MaterialTheme.spacing.extraSmall,
                                        vertical = MaterialTheme.spacing.small
                                    )
                                    .weight(1f),
                                textStyle = MaterialTheme.typography.bodyMedium.copy(
                                    textAlign = TextAlign.Center
                                )
                            )
                            Image(
                                painter = painterResource(id = CoreR.drawable.chevron_right),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .height(35.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(bounded = false),
                                        onClick = {
                                            focusManager.clearFocus()
                                            onEvent(
                                                WeaponDialogEvent.OnCountChange(
                                                    weapon,
                                                    weapon.count + 1
                                                )
                                            )
                                        },
                                    )
                                    .padding(vertical = MaterialTheme.spacing.small)
                            )
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                    )
                    Column(
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