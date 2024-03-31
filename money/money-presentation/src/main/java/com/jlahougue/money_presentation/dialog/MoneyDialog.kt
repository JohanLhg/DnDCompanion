package com.jlahougue.money_presentation.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.jlahougue.core_presentation.components.ConfirmButton
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.money_domain.model.Currency
import com.jlahougue.money_presentation.R
import com.jlahougue.money_presentation.dialog.component.CurrencyEntry
import com.jlahougue.money_presentation.dialog.component.TypeSelector

@Composable
fun MoneyDialog(
    state: MoneyDialogState,
    onEvent: (MoneyDialogEvent) -> Unit
) {
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(MoneyDialogEvent.OnDismiss) },
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .height(IntrinsicSize.Max),
        properties = DialogProperties(usePlatformDefaultWidth = false),
        header = {
            MoneyDialogHeader(onEvent)
        },
        hasContentPadding = false,
        content = {
            MoneyDialogContent(state, onEvent)
        },
        actions = {
            ConfirmButton(
                onClick = { onEvent(MoneyDialogEvent.OnConfirm) }
            )
        }
    )
}

@Composable
fun RowScope.MoneyDialogHeader(
    onEvent: (MoneyDialogEvent) -> Unit
) {
    Text(
        text = stringResource(id = R.string.money).uppercase(),
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onPrimary
    )
    Spacer(modifier = Modifier.weight(1f))
    val focusManager = LocalFocusManager.current
    Icon(
        imageVector = Icons.Default.Refresh,
        contentDescription = stringResource(id = R.string.clear),
        tint = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .size(24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                onClick = {
                    focusManager.clearFocus()
                    onEvent(MoneyDialogEvent.OnClear)
                }
            )
    )
}

@Composable
fun ColumnScope.MoneyDialogContent(
    state: MoneyDialogState,
    onEvent: (MoneyDialogEvent) -> Unit
) {
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