package com.jlahougue.health_presentation

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.components.containers.FramedBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_presentation.components.CurrentHealth
import com.jlahougue.health_presentation.components.DeathSavesIndicators
import com.jlahougue.health_presentation.components.MaxHealth
import com.jlahougue.health_presentation.components.TemporaryHealth

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

@Preview(
    apiLevel = 33,
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
                .height(IntrinsicSize.Min)
        )
    }
}