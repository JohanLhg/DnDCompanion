package com.jlahougue.property_presentation

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun PropertyDialog(
    state: PropertyDialogState,
    onEvent: (PropertyDialogEvent) -> Unit
) {
    val property = state.property ?: return
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(PropertyDialogEvent.OnDismiss) },
        modifier = Modifier
            .width(IntrinsicSize.Max),
        title = property.name
    ) {
        Text(
            text = property.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
        )
    }
}