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
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialog
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogEvent
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialog
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState

@Composable
fun EquipmentScreen(
    unitSystem: UnitSystem,
    weapons: List<WeaponInfo>,
    onWeaponEvent: (WeaponEvent) -> Unit,
    weaponDialog: WeaponDialogState,
    onWeaponDialogEvent: (WeaponDialogEvent) -> Unit,
    items: List<Item>,
    onItemEvent: (ItemEvent) -> Unit,
    itemDialog: ItemDialogState,
    onItemDialogEvent: (ItemDialogEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        WeaponList(
            weapons = weapons,
            unitSystem = unitSystem,
            onEvent = onWeaponEvent,
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
            onEvent = onItemEvent,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
    }
    WeaponDialog(
        state = weaponDialog,
        onEvent = onWeaponDialogEvent
    )
    ItemDialog(
        state = itemDialog,
        onEvent = onItemDialogEvent
    )
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
            onWeaponEvent = {},
            weaponDialog = WeaponDialogState(),
            onWeaponDialogEvent = {},
            items = listOf(),
            onItemEvent = {},
            itemDialog = ItemDialogState(),
            onItemDialogEvent = {}
        )
    }
}