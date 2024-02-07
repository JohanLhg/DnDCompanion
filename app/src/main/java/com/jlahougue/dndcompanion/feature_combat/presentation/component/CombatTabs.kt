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
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellLevelList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.data_item.presentation.Inventory
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatEvent
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatTabState

@Composable
fun CombatTabs(
    state: CombatTabState,
    onEvent: (CombatEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = state.selectedTabIndex,
        ) {
            state.tabs.forEachIndexed { index, tab ->
                TabHeader(
                    selected = state.selectedTabIndex == index,
                    onClick = {
                        onEvent(CombatEvent.onTabSelected(index))
                    },
                    title = tab.title.getString(),
                    icon = painterResource(tab.icon)
                )
            }
        }
        when (state.selectedTabIndex) {
            0 -> {
                SpellLevelList(
                    spells = state.spells,
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
                        unitSystem = state.unitSystem,
                        state = state.weapons,
                        onEvent = {
                            onEvent(CombatEvent.onWeaponEvent(it))
                        },
                        onListDialogEvent = {
                            onEvent(CombatEvent.onWeaponListDialogEvent(it))
                        },
                        onDialogEvent = {
                            onEvent(CombatEvent.onWeaponDialogEvent(it))
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                    Divider()
                    Inventory(
                        state = state.inventory,
                        onEvent = {
                            onEvent(CombatEvent.onItemEvent(it))
                        },
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