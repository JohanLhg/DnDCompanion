package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_health.presentation.HealthEvent
import com.jlahougue.health_domain.model.Health

@Composable
fun TemporaryHealth(
    health: Health,
    onEvent: (HealthEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        DividerTitle(
            title = stringResource(R.string.health_temporary),
            modifier = Modifier.fillMaxWidth()
        )
        TextFieldWithIncrements(
            value = health.temporaryHp.toString(),
            onValueChange = {
                try {
                    onEvent(HealthEvent.OnTemporaryHealthChange(it.toInt()))
                } catch (e: NumberFormatException) {
                    onEvent(HealthEvent.OnTemporaryHealthChange(0))
                }
            },
            onPlusClick = {
                onEvent(HealthEvent.OnTemporaryHealthChangeBy(1))
            },
            plusDescription = stringResource(id = R.string.health_temporary_add),
            onMinusClick = {
                onEvent(HealthEvent.OnTemporaryHealthChangeBy(-1))
            },
            minusDescription = stringResource(id = R.string.health_temporary_subtract),
            modifier = Modifier.fillMaxWidth()
        )
    }
}