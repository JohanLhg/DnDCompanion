package com.jlahougue.dndcompanion.data_health.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.FramedBox
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.presentation.components.CurrentHealth
import com.jlahougue.dndcompanion.data_health.presentation.components.DeathSavesIndicators
import com.jlahougue.dndcompanion.data_health.presentation.components.MaxHealth
import com.jlahougue.dndcompanion.data_health.presentation.components.TemporaryHealth

@Composable
fun HealthBox(
    health: Health,
    deathSaves: DeathSaves,
    onEvent: (HealthEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    FramedBox(
        title = stringResource(id = R.string.health),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MaxHealth(
                health = health,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
            CurrentHealth(
                health = health,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
            TemporaryHealth(
                health = health,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
            DeathSavesIndicators(
                deathSaves = deathSaves,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun HealthPreview() {
    DnDCompanionTheme {
        HealthBox(
            health = Health(),
            deathSaves = DeathSaves(),
            onEvent = {},
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
        )
    }
}