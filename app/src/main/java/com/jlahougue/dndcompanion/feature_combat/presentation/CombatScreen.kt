package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_ability.presentation.Abilities
import com.jlahougue.dndcompanion.data_ability.presentation.getAbilitiesPreviewData
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellEvent
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellLevelList
import com.jlahougue.dndcompanion.data_character_spell.presentation.components.SpellListMode
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
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponList
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialog
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogState

@Composable
fun CombatScreen(
    abilities: List<AbilityView>,
    stats: StatsView,
    onStatsEvent: (StatsEvent) -> Unit,
    health: Health,
    deathSaves: DeathSaves,
    onHealthEvent: (HealthEvent) -> Unit,
    mode: CombatMode,
    onModeChanged: (CombatMode) -> Unit,
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
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                Button(
                    onClick = { onModeChanged(CombatMode.SPELLS) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Spells")
                }
                Button(
                    onClick = { onModeChanged(CombatMode.WEAPONS_HEALTH) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Weapons")
                }
            }
            when (mode) {
                CombatMode.SPELLS -> {
                    SpellLevelList(
                        spells = spells,
                        modifier = Modifier
                            .fillMaxHeight(),
                        mode = SpellListMode.Prepared,
                        onEvent = onSpellEvent
                    )
                }
                CombatMode.WEAPONS_HEALTH -> {
                    WeaponList(
                        weapons = weapons,
                        unitSystem = UnitSystem.METRIC,
                        onEvent = onWeaponEvent,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
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
            mode = CombatMode.SPELLS,
            onModeChanged = {},
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