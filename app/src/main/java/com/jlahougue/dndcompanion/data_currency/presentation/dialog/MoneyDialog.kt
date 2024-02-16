package com.jlahougue.dndcompanion.data_currency.presentation.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_currency.domain.model.Currency
import com.jlahougue.dndcompanion.data_currency.presentation.dialog.component.CurrencyEntry
import com.jlahougue.dndcompanion.data_currency.presentation.dialog.component.TypeSelector

@Composable
fun MoneyDialog(
    state: MoneyDialogState,
    onEvent: (MoneyDialogEvent) -> Unit
) {
    if (!state.isShown) return
    Dialog(
        onDismissRequest = {
            onEvent(MoneyDialogEvent.OnDismiss)
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .height(IntrinsicSize.Min)
                .width(IntrinsicSize.Max),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.small)
                ) {
                    Text(
                        text = stringResource(id = R.string.money).uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .padding(vertical = MaterialTheme.spacing.small)
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    val focusManager = LocalFocusManager.current
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(id = R.string.clear),
                        modifier = Modifier
                            .size(35.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = false),
                                onClick = {
                                    focusManager.clearFocus()
                                    onEvent(MoneyDialogEvent.OnClear)
                                }
                            )
                            .padding(MaterialTheme.spacing.extraSmall)
                    )
                }
                Divider()
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        TypeSelector(
                            painter = painterResource(id = R.drawable.minus),
                            description = stringResource(id = R.string.subtract),
                            type = MoneyDialogState.MoneyDialogType.SUBTRACT,
                            state = state,
                            onEvent = onEvent
                        )
                        TypeSelector(
                            painter = painterResource(id = R.drawable.equals),
                            description = stringResource(id = R.string.set),
                            type = MoneyDialogState.MoneyDialogType.SET,
                            state = state,
                            onEvent = onEvent
                        )
                        TypeSelector(
                            painter = painterResource(id = R.drawable.plus),
                            description = stringResource(id = R.string.add),
                            type = MoneyDialogState.MoneyDialogType.ADD,
                            state = state,
                            onEvent = onEvent
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        CurrencyEntry(
                            value = state.copperPieces.toString(),
                            onValueChange = {
                                onEvent(
                                    MoneyDialogEvent.OnCopperPiecesChanged(it.toIntOrNull() ?: 0)
                                )
                            },
                            currency = Currency.COPPER
                        )
                        CurrencyEntry(
                            value = state.silverPieces.toString(),
                            onValueChange = {
                                onEvent(
                                    MoneyDialogEvent.OnSilverPiecesChanged(it.toIntOrNull() ?: 0)
                                )
                            },
                            currency = Currency.SILVER
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxHeight()
                    ) {
                        CurrencyEntry(
                            value = state.goldPieces.toString(),
                            onValueChange = {
                                onEvent(
                                    MoneyDialogEvent.OnGoldPiecesChanged(it.toIntOrNull() ?: 0)
                                )
                            },
                            currency = Currency.GOLD
                        )
                        CurrencyEntry(
                            value = state.platinumPieces.toString(),
                            onValueChange = {
                                onEvent(
                                    MoneyDialogEvent.OnPlatinumPiecesChanged(it.toIntOrNull() ?: 0)
                                )
                            },
                            currency = Currency.PLATINUM
                        )
                    }
                }
                Divider()
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { onEvent(MoneyDialogEvent.OnConfirm) },
                        shape = OutlinedTextFieldDefaults.shape,
                        contentPadding = PaddingValues(
                            vertical = MaterialTheme.spacing.small,
                            horizontal = MaterialTheme.spacing.small
                        ),
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.small,
                                vertical = MaterialTheme.spacing.extraSmall
                            )
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = null
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.confirm
                                ).uppercase(),
                                style = MaterialTheme.typography.bodyMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MoneyDialogPreview() {
    DnDCompanionTheme {
        MoneyDialog(
            state = MoneyDialogState(
                isShown = true
            ),
            onEvent = {}
        )
    }
}