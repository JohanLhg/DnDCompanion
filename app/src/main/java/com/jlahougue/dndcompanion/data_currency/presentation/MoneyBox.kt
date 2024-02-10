package com.jlahougue.dndcompanion.data_currency.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.CustomOutlinedTextField
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_currency.domain.model.Currency
import com.jlahougue.dndcompanion.data_currency.domain.model.Money

@Composable
fun MoneyBox(
    money: Money,
    onEvent: (MoneyEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.traditional_currencies).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        )
        Divider()
        Row {
            Text(
                text = Currency.COPPER.getString(money.copperPieces),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(Currency.COPPER.color),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Text(
                text = Currency.SILVER.getString(money.copperPieces),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(Currency.SILVER.color),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Text(
                text = Currency.GOLD.getString(money.copperPieces),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(Currency.GOLD.color),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Text(
                text = Currency.PLATINUM.getString(money.copperPieces),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(Currency.PLATINUM.color),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
        Text(
            text = stringResource(id = R.string.other_currencies).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        )
        Divider()
        CustomOutlinedTextField(
            value = money.otherCurrencies,
            onValueChange = {
                onEvent(
                    MoneyEvent.OnOtherCurrenciesChanged(it)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.extraSmall),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            maxLines = 5
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MoneyBowPreview() {
    DnDCompanionTheme {
        MoneyBox(
            money = Money(copperPieces = 78536),
            onEvent = {}
        )
    }
}