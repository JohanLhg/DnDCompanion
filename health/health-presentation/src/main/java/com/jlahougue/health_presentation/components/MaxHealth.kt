package com.jlahougue.health_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.text_fileds.CustomOutlinedTextField
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.health_domain.model.Health
import com.jlahougue.health_presentation.HealthEvent
import com.jlahougue.health_presentation.R

@Composable
fun MaxHealth(
    health: Health,
    onEvent: (HealthEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        DividerTitle(
            title = stringResource(R.string.health_max),
            modifier = Modifier.fillMaxWidth()
        )
        CustomOutlinedTextField(
            value = health.maxHp.toString(),
            onValueChange = {
                try {
                    onEvent(HealthEvent.OnMaxHealthChange(it.toInt()))
                } catch (e: NumberFormatException) {
                    onEvent(HealthEvent.OnMaxHealthChange(0))
                }
            },
            modifier = Modifier
                .width(75.dp)
                .padding(horizontal = MaterialTheme.spacing.small),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}