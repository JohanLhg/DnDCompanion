package com.jlahougue.equipment_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.item_presentation.Inventory
import com.jlahougue.money_presentation.MoneyBox
import com.jlahougue.property_presentation.PropertyDialog
import com.jlahougue.weapon_presentation.WeaponList
import com.jlahougue.weapon_presentation.component.WeaponStatsBox

@Composable
fun EquipmentScreen(
    state: EquipmentState,
    onEvent: (EquipmentEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            WeaponStatsBox(
                stats = state.weaponStats,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MaterialTheme.spacing.small)
            )
            HorizontalDivider()
            WeaponList(
                unitSystem = state.unitSystem,
                state = state.weapons,
                onEvent = {
                    onEvent(EquipmentEvent.OnWeaponEvent(it))
                },
                onListDialogEvent = {
                    onEvent(EquipmentEvent.OnWeaponListDialogEvent(it))
                },
                onDialogEvent = {
                    onEvent(EquipmentEvent.OnWeaponDialogEvent(it))
                },
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
        VerticalDivider()
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            MoneyBox(
                state = state.money,
                onEvent = {
                    onEvent(EquipmentEvent.OnMoneyEvent(it))
                },
                onDialogEvent = {
                    onEvent(EquipmentEvent.OnMoneyDialogEvent(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Inventory(
                state = state.inventory,
                onEvent = {
                    onEvent(EquipmentEvent.OnItemEvent(it))
                },
                onDialogEvent = {
                    onEvent(EquipmentEvent.OnItemDialogEvent(it))
                },
                modifier = Modifier
                    .fillMaxHeight()
            )
        }
    }
    PropertyDialog(
        state = state.propertyDialog,
        onEvent = {
            onEvent(EquipmentEvent.OnPropertyDialogEvent(it))
        }
    )
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun EquipmentScreenPreview() {
    DnDCompanionTheme {
        EquipmentScreen(
            state = EquipmentState(),
            onEvent = {}
        )
    }
}