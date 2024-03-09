package com.jlahougue.settings_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.settings_domain.model.UnitSystem

@Composable
fun UnitSystemSelector(
    label: String,
    unitSystem: UnitSystem,
    selectedUnitSystem: UnitSystem,
    modifier: Modifier = Modifier
) {
    Text(
        text = label,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = modifier
            .alpha(
                if (selectedUnitSystem == unitSystem) 1f
                else 0.5f
            )
            .padding(MaterialTheme.spacing.small)
            .clip(OutlinedTextFieldDefaults.shape)
            .background(MaterialTheme.colorScheme.surface)
            .padding(MaterialTheme.spacing.small)
    )
}