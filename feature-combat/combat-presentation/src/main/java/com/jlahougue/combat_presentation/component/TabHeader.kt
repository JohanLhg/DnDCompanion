package com.jlahougue.combat_presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun TabHeader(
    selected: Boolean,
    onClick: () -> Unit,
    title: String,
    icon: Painter
) {
    Tab(
        selected = selected,
        onClick = onClick,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        unselectedContentColor = MaterialTheme.colorScheme.onSurface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)
        ) {
            Icon(
                painter = icon,
                contentDescription = title,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.extraSmall)
                    .size(MaterialTheme.spacing.medium)
            )
            Text(
                text = title.uppercase(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}