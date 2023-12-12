package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_health.domain.model.Health
import com.jlahougue.dndcompanion.data_health.domain.use_case.HealthEvent

@Composable
fun CurrentHealth(
    health: Health,
    onEvent: (HealthEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        DividerTitle(
            title = stringResource(R.string.health_current),
            modifier = Modifier.fillMaxWidth()
        )
        TextFieldWithIncrements(
            value = health.currentHp.toString(),
            onValueChange = {
                try {
                    onEvent(HealthEvent.OnCurrentHealthChange(it.toInt()))
                } catch (e: NumberFormatException) {
                    onEvent(HealthEvent.OnCurrentHealthChange(0))
                }
            },
            onPlusClick = {
                onEvent(HealthEvent.OnCurrentHealthChangeBy(1))
            },
            plusDescription = stringResource(id = R.string.health_current_add),
            onMinusClick = {
                onEvent(HealthEvent.OnCurrentHealthChangeBy(-1))
            },
            minusDescription = stringResource(id = R.string.health_current_subtract),
            modifier = Modifier.fillMaxWidth()
        )
    }
}