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
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellLevelList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.Inventory
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList

@Composable
fun CombatTabs(
    tabState: TabState,
    onTabSelected: (Int) -> Unit,
    spells: List<SpellLevel>,
    onSpellEvent: (SpellEvent) -> Unit,
    unitSystem: UnitSystem,
    weapons: List<WeaponInfo>,
    onWeaponEvent: (WeaponEvent) -> Unit,
    items: List<Item>,
    onItemEvent: (ItemEvent) -> Unit,
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
                    onClick = { onTabSelected(index) },
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
                    onEvent = onSpellEvent
                )
            }
            1 -> {
                Column(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                ) {
                    WeaponList(
                        weapons = weapons,
                        unitSystem = unitSystem,
                        onEvent = onWeaponEvent,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Divider()
                    Inventory(
                        items = items,
                        onEvent = onItemEvent,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        }
    }
}