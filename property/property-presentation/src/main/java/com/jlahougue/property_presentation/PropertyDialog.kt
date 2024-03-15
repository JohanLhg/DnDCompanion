package com.jlahougue.property_presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun PropertyDialog(
    state: PropertyDialogState,
    onEvent: (PropertyDialogEvent) -> Unit
) {
    if (!state.isShown) return
    val property = state.property ?: return
    Dialog(
        onDismissRequest = {
            onEvent(PropertyDialogEvent.OnDismiss)
        }
    ) {
        Card(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(IntrinsicSize.Max),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Text(
                text = property.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .padding(
                        vertical = MaterialTheme.spacing.small,
                        horizontal = MaterialTheme.spacing.extraSmall
                    )
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
            Text(
                text = property.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small + MaterialTheme.spacing.extraSmall)
                    .padding(vertical = MaterialTheme.spacing.small)
            )
        }
    }
}