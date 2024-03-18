package com.jlahougue.money_presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.text_fileds.CustomOutlinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.money_domain.model.Currency
import com.jlahougue.money_domain.model.Money
import com.jlahougue.money_presentation.dialog.MoneyDialog
import com.jlahougue.money_presentation.dialog.MoneyDialogEvent

@Composable
fun MoneyBox(
    state: MoneyState,
    onEvent: (MoneyEvent) -> Unit,
    onDialogEvent: (MoneyDialogEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.traditional_currencies).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.edit),
                modifier = Modifier
                    .size(40.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            onEvent(MoneyEvent.OnDialogOpen)
                        }
                    )
                    .padding(MaterialTheme.spacing.small)
            )
        }
        HorizontalDivider()
        Row {
            Text(
                text = Currency.COPPER.getString(state.money.copperPieces),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(Currency.COPPER.color),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Text(
                text = Currency.SILVER.getString(state.money.copperPieces),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(Currency.SILVER.color),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Text(
                text = Currency.GOLD.getString(state.money.copperPieces),
                style = MaterialTheme.typography.bodyLarge,
                color = Color(Currency.GOLD.color),
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Text(
                text = Currency.PLATINUM.getString(state.money.copperPieces),
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
        HorizontalDivider()
        CustomOutlinedTextField(
            value = state.money.otherCurrencies,
            onValueChange = {
                onEvent(
                    MoneyEvent.OnOtherCurrenciesChanged(state.money, it)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    min = 60.dp,
                    max = 120.dp
                )
                .padding(MaterialTheme.spacing.extraSmall),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            maxLines = Int.MAX_VALUE
        )
    }
    MoneyDialog(
        state = state.dialog,
        onEvent = onDialogEvent
    )
}

@Preview(
    apiLevel = 33,
    showBackground = true
)
@Composable
fun MoneyBowPreview() {
    DnDCompanionTheme {
        MoneyBox(
            state = MoneyState(money = Money(copperPieces = 78536)),
            onEvent = {},
            onDialogEvent = {}
        )
    }
}