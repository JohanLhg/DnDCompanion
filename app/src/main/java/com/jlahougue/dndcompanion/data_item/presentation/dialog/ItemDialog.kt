package com.jlahougue.dndcompanion.data_item.presentation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.CustomOutlinedTextField
import com.jlahougue.dndcompanion.core.presentation.components.PropertyEditColumn
import com.jlahougue.dndcompanion.core.presentation.components.PropertyEditRow
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_currency.domain.model.Currency
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.dialog.component.CostEditRow

@Composable
fun ItemDialog(
    state: ItemDialogState,
    onEvent: (ItemDialogEvent) -> Unit
) {
    val item = state.item ?: return
    if (state.isShown) {
        Dialog(
            onDismissRequest = {
                onEvent(ItemDialogEvent.OnDismiss)
            }
        ) {
            Card(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    ),
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
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.small)
                    ) {
                        CustomOutlinedTextField(
                            value = item.name,
                            onValueChange = {
                                onEvent(ItemDialogEvent.OnNameChanged(item, it))
                            },
                            modifier = Modifier
                                .padding(
                                    horizontal = MaterialTheme.spacing.extraSmall,
                                    vertical = MaterialTheme.spacing.small
                                ),
                            textStyle = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start = MaterialTheme.spacing.small)
                                .sizeIn(maxWidth = 100.dp)
                        ) {
                            val focusManager = LocalFocusManager.current
                            Image(
                                painter = painterResource(id = R.drawable.chevron_left),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight,
                                alpha = if (item.quantity <= 0) 0.2f else 1f,
                                modifier = Modifier
                                    .height(35.dp)
                                    .clickable(
                                        enabled = item.quantity > 0,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(bounded = false),
                                        onClick = {
                                            focusManager.clearFocus()
                                            onEvent(
                                                ItemDialogEvent.OnQuantityChanged(
                                                    item,
                                                    item.quantity - 1
                                                )
                                            )
                                        },
                                    )
                                    .padding(vertical = MaterialTheme.spacing.small)
                            )
                            CustomOutlinedTextField(
                                value = item.quantity.toString(),
                                onValueChange = {
                                    onEvent(
                                        ItemDialogEvent.OnQuantityChanged(
                                            item,
                                            it.toIntOrNull() ?: 0
                                        ))
                                },
                                modifier = Modifier
                                    .padding(
                                        horizontal = MaterialTheme.spacing.extraSmall,
                                        vertical = MaterialTheme.spacing.small
                                    )
                                    .weight(1f),
                                textStyle = MaterialTheme.typography.bodyMedium.copy(
                                    textAlign = TextAlign.Center
                                )
                            )
                            Image(
                                painter = painterResource(id = R.drawable.chevron_right),
                                contentDescription = null,
                                contentScale = ContentScale.FillHeight,
                                modifier = Modifier
                                    .height(35.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(bounded = false),
                                        onClick = {
                                            focusManager.clearFocus()
                                            onEvent(
                                                ItemDialogEvent.OnQuantityChanged(
                                                    item,
                                                    item.quantity + 1
                                                )
                                            )
                                        },
                                    )
                                    .padding(vertical = MaterialTheme.spacing.small)
                            )
                        }
                    }
                    Divider(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                    )
                    Column(
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
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
                            label = stringResource(id = R.string.weapon_description),
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
                            maxLines = 5
                        )
                    }
                }
            }
        }
    }
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