package com.jlahougue.dndcompanion.feature_combat.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_character_spell.presentation.dialog.SpellDialog
import com.jlahougue.dndcompanion.data_health.presentation.HealthBox
import com.jlahougue.dndcompanion.data_stats.presentation.StatsList
import com.jlahougue.dndcompanion.feature_combat.presentation.component.CombatTabs
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabItem

@Composable
fun CombatScreen(
    state: CombatState,
    onEvent: (CombatEvent) -> Unit
) {
    Row {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
        ) {
            com.jlahougue.ability_presentation.Abilities(
                abilities = state.abilities,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            )
            StatsList(
                stats = state.stats,
                onEvent = {
                    onEvent(CombatEvent.onStatsEvent(it))
                },
                modifier = Modifier
                    .height(IntrinsicSize.Max)
            )
        }
        HealthBox(
            health = state.health,
            deathSaves = state.deathSaves,
            onEvent = {
                onEvent(CombatEvent.onHealthEvent(it))
            },
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        )
        VerticalDivider()
        CombatTabs(
            state = state.tab,
            onEvent = onEvent,
            modifier = Modifier.fillMaxSize()
        )
    }
    SpellDialog(
        state = state.spellDialog,
        onEvent = {
            onEvent(CombatEvent.onSpellDialogEvent(it))
        }
    )
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun CombatScreenPreview() {
    DnDCompanionTheme {
        CombatScreen(
            state = CombatState(
                abilities = com.jlahougue.ability_presentation.getAbilitiesPreviewData(),
                tab = CombatTabState(
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
                )
            ),
            onEvent = {}
        )
    }
}