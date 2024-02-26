package com.jlahougue.dndcompanion.data_currency.presentation.dialog.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.data_currency.presentation.dialog.MoneyDialogEvent
import com.jlahougue.dndcompanion.data_currency.presentation.dialog.MoneyDialogState

@Composable
fun TypeSelector(
    painter: Painter,
    description: String,
    type: MoneyDialogState.MoneyDialogType,
    state: MoneyDialogState,
    onEvent: (MoneyDialogEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    OutlinedButton(
        onClick = {
            focusManager.clearFocus()
            onEvent(MoneyDialogEvent.OnTypeChanged(type))
        },
        shape = OutlinedTextFieldDefaults.shape,
        contentPadding = PaddingValues(
            vertical = MaterialTheme.spacing.small,
            horizontal = MaterialTheme.spacing.small
        ),
        modifier = Modifier
            .padding(MaterialTheme.spacing.extraSmall)
            .size(30.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = description,
            colorFilter = ColorFilter.tint(
                if (state.type == type) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface
            )
        )
    }
}