package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_ability.domain.use_case.AbilityEvent
import com.jlahougue.dndcompanion.data_ability.presentation.Abilities
import com.jlahougue.dndcompanion.data_ability.presentation.getAbilitiesPreviewData
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.presentation.SpellList
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.presentation.HealthBox
import com.jlahougue.dndcompanion.data_health.presentation.HealthEvent
import com.jlahougue.dndcompanion.data_stats.domain.model.Stats
import com.jlahougue.dndcompanion.data_stats.presentation.StatsEvent
import com.jlahougue.dndcompanion.data_stats.presentation.StatsList

@Composable
fun CombatScreen(
    abilities: List<AbilityView>,
    onAbilityEvent: (AbilityEvent) -> Unit,
    stats: Stats,
    onStatsEvent: (StatsEvent) -> Unit,
    health: Health,
    deathSaves: DeathSaves,
    onHealthEvent: (HealthEvent) -> Unit,
    spells: List<SpellLevel> = listOf()
) {
    Row {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
        ) {
            Abilities(
                abilities = abilities,
                onEvent = onAbilityEvent,
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
        SpellList(
            spells = spells,
            modifier = Modifier.fillMaxHeight()
        )
    }
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
            onAbilityEvent = {},
            stats = Stats(),
            onStatsEvent = {},
            health = Health(),
            deathSaves = DeathSaves(),
            onHealthEvent = {}
        )
    }
}