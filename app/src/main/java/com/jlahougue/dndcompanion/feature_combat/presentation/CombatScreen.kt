package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_ability.presentation.Abilities
import com.jlahougue.dndcompanion.data_ability.presentation.getAbilitiesPreviewData
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialog
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogState
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.presentation.HealthBox
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.dialog.ItemDialogState
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_stats.domain.model.StatsView
import com.jlahougue.dndcompanion.data_stats.presentation.StatsList
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogState
import com.jlahougue.dndcompanion.feature_combat.presentation.component.CombatTabs
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabItem
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabState

@Composable
fun CombatScreen(
    abilities: List<AbilityView>,
    stats: StatsView,
    health: Health,
    deathSaves: DeathSaves,
    tabState: TabState,
    unitSystem: UnitSystem,
    weapons: List<WeaponInfo>,
    weaponListDialogState: WeaponListDialogState,
    weaponDialogState: WeaponDialogState,
    items: List<Item>,
    itemDialogState: ItemDialogState,
    spells: List<SpellLevel>,
    spellDialogState: SpellDialogState,
    onEvent: (CombatEvent) -> Unit
) {
    Row {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
        ) {
            Abilities(
                abilities = abilities,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            )
            StatsList(
                stats = stats,
                onEvent = {
                    onEvent(CombatEvent.onStatsEvent(it))
                },
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            )
        }
        HealthBox(
            health = health,
            deathSaves = deathSaves,
            onEvent = {
                onEvent(CombatEvent.onHealthEvent(it))
            },
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        )
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
        CombatTabs(
            tabState = tabState,
            spells = spells,
            unitSystem = unitSystem,
            weapons = weapons,
            weaponListDialog = weaponListDialogState,
            weaponDialog = weaponDialogState,
            items = items,
            itemDialog = itemDialogState,
            onEvent = onEvent,
            modifier = Modifier.fillMaxSize()
        )
    }
    SpellDialog(
        state = spellDialogState,
        onEvent = {
            onEvent(CombatEvent.onSpellDialogEvent(it))
        }
    )
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun CombatScreenPreview() {
    DnDCompanionTheme {
        CombatScreen(
            abilities = getAbilitiesPreviewData(),
            stats = StatsView(),
            health = Health(),
            deathSaves = DeathSaves(),
            tabState = TabState(
                tabs = listOf(
                    TabItem(
                        title = UiText.StringResource(R.string.spells),
                        icon = R.drawable.spell_book
                    ),
                    TabItem(
                        title = UiText.StringResource(R.string.weapons),
                        icon = R.drawable.weapons
                    )
                ),
                selectedTabIndex = 0
            ),
            unitSystem = UnitSystem.METRIC,
            weapons = listOf(),
            weaponListDialogState = WeaponListDialogState(),
            weaponDialogState = WeaponDialogState(),
            items = listOf(),
            itemDialogState = ItemDialogState(),
            spells = listOf(),
            spellDialogState = SpellDialogState(),
            onEvent = {}
        )
    }
}