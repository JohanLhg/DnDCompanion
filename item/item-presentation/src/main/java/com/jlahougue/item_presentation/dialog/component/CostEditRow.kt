package com.jlahougue.item_presentation.dialog.component

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.components.dropdown.CustomDropDown
import com.jlahougue.core_presentation.components.dropdown.DropDownItem
import com.jlahougue.core_presentation.components.text_fileds.CustomOutlinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.money_domain.model.Currency

@Composable
fun CostEditRow(
    label: String,
    value: String,
    onValueChange: (Int) -> Unit,
    currency: Currency,
    onCurrencyChange: (Currency) -> Unit,
    modifier: Modifier = Modifier
) {
    val options by remember {
        mutableStateOf(Currency.entries.map {
            DropDownItem(
                value = it.name,
                label = it.label
            )
        })
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = MaterialTheme.spacing.small)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        CustomOutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it.toIntOrNull() ?: 0)
            },
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.extraSmall)
                .width(IntrinsicSize.Min),
            textStyle = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
        CustomDropDown(
            value = currency.shortLabel.getString(),
            options = options,
            onOptionSelected = {
                onCurrencyChange(Currency.from(it))
            },
            modifier = Modifier.width(IntrinsicSize.Max)
        )
    }
}

@Preview
@Composable
fun CostEditRowPreview() {
    DnDCompanionTheme {
        CostEditRow(
            label = "Name :",
            value = "25",
            onValueChange = {},
            currency = Currency.COPPER,
            onCurrencyChange = {}
        )
    }
}