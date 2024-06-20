package com.jlahougue.roaming.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.ability_presentation.Abilities
import com.jlahougue.ability_presentation.getAbilitiesPreviewData
import com.jlahougue.core_presentation.components.ActionButton
import com.jlahougue.core_presentation.components.containers.FramedBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.health_presentation.components.CurrentHealth
import com.jlahougue.health_presentation.components.MaxHealth
import com.jlahougue.health_presentation.components.TemporaryHealth
import com.jlahougue.item_presentation.Inventory
import com.jlahougue.skill_presentation.Skills
import com.jlahougue.skill_presentation.getSkillsPreviewData
import com.jlahougue.health_presentation.R as HealthR

@Composable
fun RoamingScreenRoot(
    viewModel: RoamingViewModel
) {
    val state by viewModel.state.collectAsState()

    RoamingScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun RoamingScreen(
    state: RoamingState,
    onAction: (RoamingAction) -> Unit
) {
    Row {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max)
        ) {
            Abilities(
                abilities = state.abilities,
                modifier = Modifier.height(IntrinsicSize.Max)
            )
            FramedBox(
                title = stringResource(id = HealthR.string.health),
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Max)
            ) {
                MaxHealth(
                    health = state.health,
                    onEvent = {
                        onAction(RoamingAction.OnHealthAction(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                CurrentHealth(
                    health = state.health,
                    onEvent = {
                        onAction(RoamingAction.OnHealthAction(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                TemporaryHealth(
                    health = state.health,
                    onEvent = {
                        onAction(RoamingAction.OnHealthAction(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            ActionButton(
                label = stringResource(id = R.string.short_rest),
                onClick = {
                    onAction(RoamingAction.OnShortRest)
                },
                modifier = Modifier.fillMaxWidth()
            )
            ActionButton(
                label = stringResource(id = R.string.long_rest),
                onClick = {
                    onAction(RoamingAction.OnLongRest)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Skills(
            skills = state.skills,
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Max)
        )
        VerticalDivider()
        Inventory(
            state = state.inventory,
            onEvent = {
                onAction(RoamingAction.OnItemAction(it))
            },
            onDialogEvent = {
                onAction(RoamingAction.OnItemDialogAction(it))
            },
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
private fun RoamingPreview() {
    DnDCompanionTheme {
        RoamingScreen(
            state = RoamingState(
                abilities = getAbilitiesPreviewData(),
                skills = getSkillsPreviewData(),
            ),
            onAction = { }
        )
    }
}