package com.jlahougue.core_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun PropertyEditColumn(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = Int.MAX_VALUE
) {
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
        CustomOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = textFieldModifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.extraSmall),
            textStyle = MaterialTheme.typography.bodySmall,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines
        )
    }
}