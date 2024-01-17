package com.jlahougue.dndcompanion.data_item.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.CustomOutlinedTextField
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.ItemEvent

@Composable
fun ItemCard(
    item: Item,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(
                onClick = {
                    onEvent(ItemEvent.OnItemClicked(item.id))
                }
            )
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "x" + item.quantity,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
            )
        }
        Divider(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )
        Column (
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
        ) {
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.small),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        }
    }
}

@Composable
fun ItemCardBis(
    item: Item,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(MaterialTheme.spacing.extraSmall)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium
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
                        .height(25.dp)
                        .clickable(
                            enabled = item.quantity > 0,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {
                                focusManager.clearFocus()
                                onEvent(
                                    ItemEvent.OnQuantityChanged(
                                        item,
                                        item.quantity - 1
                                    )
                                )
                            },
                        )
                )
                CustomOutlinedTextField(
                    value = item.quantity.toString(),
                    onValueChange = {
                        onEvent(
                            ItemEvent.OnQuantityChanged(
                                item,
                                it.toIntOrNull() ?: 0
                            ))
                    },
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.extraSmall)
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
                        .height(25.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {
                                focusManager.clearFocus()
                                onEvent(
                                    ItemEvent.OnQuantityChanged(
                                        item,
                                        item.quantity + 1
                                    )
                                )
                            },
                        )
                )
            }
        }
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.small),
            overflow = TextOverflow.Ellipsis,
            maxLines = 3
        )
    }
}

@Preview
@Composable
fun ItemCardPreview() {
    DnDCompanionTheme {
        ItemCard(
            item = Item(
                cid = 1,
                id = 1,
                quantity = 1,
                name = "Item",
                description = "Description",
                cost = 10,
                weight = 1
            ),
            onEvent = {}
        )
    }
}