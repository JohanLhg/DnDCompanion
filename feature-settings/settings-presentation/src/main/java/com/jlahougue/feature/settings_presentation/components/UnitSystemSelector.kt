package com.jlahougue.feature.settings_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.user_info_domain.model.UnitSystem

@Composable
fun UnitSystemSelector(
    label: String,
    unitSystem: UnitSystem,
    selectedUnitSystem: UnitSystem,
    onEvent: (UnitSystem) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = if (selectedUnitSystem == unitSystem) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface
        ),
        textAlign = TextAlign.Center,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onEvent(unitSystem) }
            )
            .padding(MaterialTheme.spacing.medium)
            .clip(OutlinedTextFieldDefaults.shape)
            .background(
                color = if (selectedUnitSystem == unitSystem) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            )
            .padding(MaterialTheme.spacing.small)
    )
}