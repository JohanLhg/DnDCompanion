package com.jlahougue.health_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun DividerTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        HorizontalDivider(modifier.weight(1f))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        )
        HorizontalDivider(modifier.weight(1f))
    }
}