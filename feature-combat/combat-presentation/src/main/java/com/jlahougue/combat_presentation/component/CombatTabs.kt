package com.jlahougue.combat_presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jlahougue.character_spell_presentation.SpellLevelList
import com.jlahougue.character_spell_presentation.components.SpellListMode
import com.jlahougue.character_spell_presentation.components.SpellcastingStatsRow
import com.jlahougue.combat_presentation.CombatEvent
import com.jlahougue.combat_presentation.CombatTabState
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.item_presentation.Inventory
import com.jlahougue.weapon_presentation.WeaponList

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
                        onEvent(CombatEvent.OnTabSelected(index))
                    },
                    title = tab.title.getString(),
                    icon = painterResource(tab.icon)
                )
            }
        }
        when (state.selectedTabIndex) {
            0 -> {
                Column {
                    SpellcastingStatsRow(
                        spellcasting = state.spellcasterStats,
                        modifier = Modifier.fillMaxWidth()
                    )
                    HorizontalDivider()
                    SpellLevelList(
                        spells = state.spells,
                        modifier = Modifier
                            .fillMaxHeight(),
                        mode = SpellListMode.Prepared,
                        onEvent = { onEvent(CombatEvent.OnSpellEvent(it)) }
                    )
                }
            }

            1 -> {
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                ) {
                    WeaponList(
                        unitSystem = state.unitSystem,
                        state = state.weapons,
                        onEvent = {
                            onEvent(CombatEvent.OnWeaponEvent(it))
                        },
                        onListDialogEvent = {
                            onEvent(CombatEvent.OnWeaponListDialogEvent(it))
                        },
                        onDialogEvent = {
                            onEvent(CombatEvent.OnWeaponDialogEvent(it))
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                    HorizontalDivider()
                    Inventory(
                        state = state.inventory,
                        onEvent = {
                            onEvent(CombatEvent.OnItemEvent(it))
                        },
                        onDialogEvent = {
                            onEvent(CombatEvent.OnItemDialogEvent(it))
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}