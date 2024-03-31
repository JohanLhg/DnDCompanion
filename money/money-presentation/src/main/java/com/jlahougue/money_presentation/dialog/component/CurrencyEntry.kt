package com.jlahougue.money_presentation.dialog.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.text_fileds.CustomOutlinedTextField
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.money_domain.model.Currency
import com.jlahougue.money_presentation.util.asColor
import com.jlahougue.money_presentation.util.asShortUiText

@Composable
fun CurrencyEntry(
    value: String,
    onValueChange: (String) -> Unit,
    currency: Currency
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .width(100.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            maxLines = 1
        )
        Text(
            text = currency.asShortUiText().getString(),
            style = MaterialTheme.typography.titleLarge,
            color = currency.asColor(),
            modifier = Modifier
                .padding(vertical = MaterialTheme.spacing.small)
                .padding(horizontal = MaterialTheme.spacing.extraSmall)
        )
    }
}