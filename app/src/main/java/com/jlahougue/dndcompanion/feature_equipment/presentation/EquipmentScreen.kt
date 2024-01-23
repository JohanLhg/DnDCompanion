package com.jlahougue.dndcompanion.feature_equipment.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.Inventory
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList

@Composable
fun EquipmentScreen(
    unitSystem: UnitSystem,
    weapons: List<WeaponInfo>,
    onWeaponEvent: (WeaponEvent) -> Unit,
    items: List<Item>,
    onItemEvent: (ItemEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        WeaponList(
            weapons = weapons,
            unitSystem = unitSystem,
            onEvent = onWeaponEvent,
            modifier = Modifier.weight(1f)
        )
        Inventory(
            items = items,
            onEvent = onItemEvent,
            modifier = Modifier.weight(1f)
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
            onWeaponEvent = {},
            items = listOf(),
            onItemEvent = {}
        )
    }
}