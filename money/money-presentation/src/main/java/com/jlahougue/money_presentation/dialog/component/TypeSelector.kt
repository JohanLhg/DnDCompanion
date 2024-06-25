package com.jlahougue.money_presentation.dialog.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.money_presentation.R
import com.jlahougue.money_presentation.dialog.MoneyDialogEvent
import com.jlahougue.money_presentation.dialog.MoneyDialogState

@Composable
fun TypeSelector(
    painter: Painter,
    description: String,
    type: MoneyDialogState.MoneyDialogType,
    selectedType: MoneyDialogState.MoneyDialogType,
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
        border = BorderStroke(
            width = 1.dp,
            color = if (selectedType == type) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .padding(MaterialTheme.spacing.extraSmall)
            .background(
                color = if (selectedType == type) MaterialTheme.colorScheme.primary
                else Color.Transparent,
                shape = OutlinedTextFieldDefaults.shape
            )
            .size(30.dp)
    ) {
        Image(
            painter = painter,
            contentDescription = description,
            colorFilter = ColorFilter.tint(
                if (selectedType == type) MaterialTheme.colorScheme.onPrimary
                else MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Preview
@Composable
private fun TypeSelectorPreview() {
    DnDCompanionTheme {
        TypeSelector(
            painter = painterResource(id = R.drawable.equals),
            description = "Equals",
            type = MoneyDialogState.MoneyDialogType.SET,
            selectedType = MoneyDialogState.MoneyDialogType.ADD,
            onEvent = {}
        )
    }
}