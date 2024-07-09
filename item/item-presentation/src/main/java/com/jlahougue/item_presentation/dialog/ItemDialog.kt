package com.jlahougue.item_presentation.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.components.labeled_values.PropertyEditColumn
import com.jlahougue.core_presentation.components.labeled_values.PropertyEditRow
import com.jlahougue.core_presentation.components.text_fileds.Counter
import com.jlahougue.core_presentation.components.text_fileds.CustomOutlinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.item_domain.model.Item
import com.jlahougue.item_presentation.R
import com.jlahougue.item_presentation.dialog.component.CostEditRow
import com.jlahougue.money_domain.model.Currency

@Composable
fun ItemDialog(
    state: ItemDialogState,
    onEvent: (ItemDialogEvent) -> Unit
) {
    val item = state.item ?: return
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = {
            onEvent(ItemDialogEvent.OnDismiss)
        },
        header = {
            ItemDialogHeader(
                item = item,
                onEvent = onEvent
            )
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            CostEditRow(
                label = stringResource(id = R.string.item_cost),
                value = item.cost.toString(),
                onValueChange = {
                    onEvent(
                        ItemDialogEvent.OnCostChanged(
                            item,
                            it
                        )
                    )
                },
                currency = item.currency,
                onCurrencyChange = {
                    onEvent(
                        ItemDialogEvent.OnCurrencyChanged(
                            item,
                            it
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            )
            PropertyEditRow(
                label = stringResource(id = R.string.item_weight),
                value = item.weight.toString(),
                onValueChange = {
                    onEvent(
                        ItemDialogEvent.OnWeightChanged(
                            item,
                            it.toFloatOrNull() ?: 0f
                        )
                    )
                },
                modifier = Modifier.weight(1f),
                textFieldModifier = Modifier.width(IntrinsicSize.Max),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
        }
        PropertyEditColumn(
            label = stringResource(id = R.string.item_description),
            value = item.description,
            onValueChange = {
                onEvent(
                    ItemDialogEvent.OnDescriptionChanged(
                        item,
                        it
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.extraSmall),
            textFieldModifier = Modifier.heightIn(min = 100.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences
            ),
            maxLines = 5
        )
    }
}

@Composable
fun RowScope.ItemDialogHeader(
    item: Item,
    onEvent: (ItemDialogEvent) -> Unit
) {
    CustomOutlinedTextField(
        value = item.name,
        onValueChange = {
            onEvent(ItemDialogEvent.OnNameChanged(item, it))
        },
        borderColor = Color.Gray,
        textStyle = MaterialTheme.typography.titleMedium.copy(
            color = MaterialTheme.colorScheme.onPrimary
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Words
        )
    )
    Spacer(modifier = Modifier.weight(1f))
    Counter(
        quantity = item.quantity,
        onQuantityChanged = {
            onEvent(
                ItemDialogEvent.OnQuantityChanged(item, it)
            )
        },
        modifier = Modifier.width(100.dp)
    )
    Icon(
        imageVector = Icons.Outlined.Delete,
        contentDescription = stringResource(id = R.string.delete_item),
        tint = MaterialTheme.colorScheme.error,
        modifier = Modifier
            .height(24.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
                onClick = {
                    onEvent(
                        ItemDialogEvent.OnDelete(item)
                    )
                },
            )
    )
}

@Preview
@Composable
fun ItemDialogPreview() {
    DnDCompanionTheme {
        ItemDialog(
            state = ItemDialogState(
                item = Item(
                    cid = 1,
                    id = 1,
                    name = "Dagger",
                    cost = 2,
                    currency = Currency.GOLD,
                    weight = 3.5f,
                    description = "A dagger is a small, easy-to-conceal blade with a sharp point. It is made for thrusting attacks, but the edge can be used in a slash as well.",
                    quantity = 1
                ),
                isShown = true
            ),
            onEvent = {}
        )
    }
}