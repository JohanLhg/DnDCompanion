package com.jlahougue.dndcompanion.feature_equipment.presentation

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
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.Inventory
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogState

@Composable
fun EquipmentScreen(
    unitSystem: UnitSystem,
    weapons: List<WeaponInfo>,
    weaponListDialog: WeaponListDialogState,
    weaponDialog: WeaponDialogState,
    items: List<Item>,
    itemDialog: ItemDialogState,
    onEvent: (EquipmentEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        WeaponList(
            weapons = weapons,
            unitSystem = unitSystem,
            onEvent = {
                onEvent(EquipmentEvent.OnWeaponEvent(it))
            },
            listDialogState = weaponListDialog,
            onListDialogEvent = {
                onEvent(EquipmentEvent.OnWeaponListDialogEvent(it))
            },
            dialogState = weaponDialog,
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
        Inventory(
            items = items,
            onEvent = {
                onEvent(EquipmentEvent.OnItemEvent(it))
            },
            dialog = itemDialog,
            onDialogEvent = {
                onEvent(EquipmentEvent.OnItemDialogEvent(it))
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
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
            unitSystem = UnitSystem.METRIC,
            weapons = listOf(),
            weaponListDialog = WeaponListDialogState(),
            weaponDialog = WeaponDialogState(),
            items = listOf(),
            itemDialog = ItemDialogState(),
            onEvent = {}
        )
    }
}