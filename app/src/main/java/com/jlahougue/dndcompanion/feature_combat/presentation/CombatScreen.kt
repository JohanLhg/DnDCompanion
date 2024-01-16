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
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialog
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialogState
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.presentation.HealthBox
import com.jlahougue.dndcompanion.data_health.presentation.HealthEvent
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_stats.domain.model.StatsView
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import com.jlahougue.dndcompanion.data_stats.presentation.StatsList
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialog
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState
import com.jlahougue.dndcompanion.feature_combat.presentation.component.CombatTabs
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabItem
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabState

@Composable
fun CombatScreen(
    abilities: List<AbilityView>,
    stats: StatsView,
    onStatsEvent: (StatsEvent) -> Unit,
    health: Health,
    deathSaves: DeathSaves,
    onHealthEvent: (HealthEvent) -> Unit,
    tabState: TabState,
    onTabSelected: (Int) -> Unit,
    unitSystem: UnitSystem,
    weapons: List<WeaponInfo>,
    onWeaponEvent: (WeaponEvent) -> Unit,
    weaponDialogState: WeaponDialogState,
    onWeaponDialogEvent: (WeaponDialogEvent) -> Unit,
    spells: List<SpellLevel>,
    onSpellEvent: (SpellEvent) -> Unit,
    spellDialogState: SpellDialogState,
    onSpellDialogEvent: (SpellDialogEvent) -> Unit
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
                onEvent = onStatsEvent,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            )
        }
        HealthBox(
            health = health,
            deathSaves = deathSaves,
            onEvent = onHealthEvent,
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
            onTabSelected = onTabSelected,
            spells = spells,
            onSpellEvent = onSpellEvent,
            unitSystem = unitSystem,
            weapons = weapons,
            onWeaponEvent = onWeaponEvent,
            modifier = Modifier.fillMaxSize()
        )
    }
    SpellDialog(
        state = spellDialogState,
        onEvent = onSpellDialogEvent
    )
    WeaponDialog(
        state = weaponDialogState,
        onEvent = onWeaponDialogEvent
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
            onStatsEvent = {},
            health = Health(),
            deathSaves = DeathSaves(),
            onHealthEvent = {},
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
            onTabSelected = {},
            unitSystem = UnitSystem.METRIC,
            weapons = listOf(),
            onWeaponEvent = {},
            weaponDialogState = WeaponDialogState(),
            onWeaponDialogEvent = {},
            spells = listOf(),
            onSpellEvent = {},
            spellDialogState = SpellDialogState(),
            onSpellDialogEvent = {}
        )
    }
}