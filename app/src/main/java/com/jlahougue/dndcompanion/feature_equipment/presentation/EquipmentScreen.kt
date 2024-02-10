package com.jlahougue.dndcompanion.feature_equipment.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_currency.domain.model.Money
import com.jlahougue.dndcompanion.data_currency.presentation.MoneyBox
import com.jlahougue.dndcompanion.data_item.presentation.Inventory
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList

@Composable
fun EquipmentScreen(
    state: EquipmentState,
    onEvent: (EquipmentEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
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
                .weight(1f)
                .fillMaxHeight()
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            MoneyBox(money = Money(copperPieces = 78536), onEvent = {})
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
}

@Preview(
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