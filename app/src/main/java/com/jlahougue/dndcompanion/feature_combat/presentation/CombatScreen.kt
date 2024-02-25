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
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_ability.presentation.Abilities
import com.jlahougue.dndcompanion.data_ability.presentation.getAbilitiesPreviewData
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
            Abilities(
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
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )
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
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun CombatScreenPreview() {
    DnDCompanionTheme {
        CombatScreen(
            state = CombatState(
                abilities = getAbilitiesPreviewData(),
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