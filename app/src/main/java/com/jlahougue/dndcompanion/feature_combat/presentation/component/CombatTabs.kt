package com.jlahougue.dndcompanion.feature_combat.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellLevelList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.Inventory
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogState
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatEvent

@Composable
fun CombatTabs(
    tabState: TabState,
    spells: List<SpellLevel>,
    unitSystem: UnitSystem,
    weapons: List<WeaponInfo>,
    weaponListDialog: WeaponListDialogState,
    weaponDialog: WeaponDialogState,
    items: List<Item>,
    itemDialog: ItemDialogState,
    onEvent: (CombatEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = tabState.selectedTabIndex,
        ) {
            tabState.tabs.forEachIndexed { index, tab ->
                TabHeader(
                    selected = tabState.selectedTabIndex == index,
                    onClick = {
                        onEvent(CombatEvent.onTabSelected(index))
                    },
                    title = tab.title.getString(),
                    icon = painterResource(tab.icon)
                )
            }
        }
        when (tabState.selectedTabIndex) {
            0 -> {
                SpellLevelList(
                    spells = spells,
                    modifier = Modifier
                        .fillMaxHeight(),
                    mode = SpellListMode.Prepared,
                    onEvent = { onEvent(CombatEvent.onSpellEvent(it)) }
                )
            }
            1 -> {
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                ) {
                    WeaponList(
                        weapons = weapons,
                        unitSystem = unitSystem,
                        onEvent = {
                            onEvent(CombatEvent.onWeaponEvent(it))
                        },
                        listDialogState = weaponListDialog,
                        onListDialogEvent = {
                            onEvent(CombatEvent.onWeaponListDialogEvent(it))
                        },
                        dialogState = weaponDialog,
                        onDialogEvent = {
                            onEvent(CombatEvent.onWeaponDialogEvent(it))
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                    Divider()
                    Inventory(
                        items = items,
                        onEvent = {
                            onEvent(CombatEvent.onItemEvent(it))
                        },
                        dialog = itemDialog,
                        onDialogEvent = {
                            onEvent(CombatEvent.onItemDialogEvent(it))
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}