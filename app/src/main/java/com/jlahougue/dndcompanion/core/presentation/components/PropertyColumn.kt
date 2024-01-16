package com.jlahougue.dndcompanion.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.jlahougue.dndcompanion.core.presentation.theme.spacing

@Composable
fun PropertyColumn(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE
) {
    if (value.isBlank()) return
    Column(
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small),
            overflow = TextOverflow.Ellipsis,
            maxLines = maxLines
        )
    }
}