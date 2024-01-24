package com.jlahougue.dndcompanion.data_item.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_currency.domain.model.Currency
import com.jlahougue.dndcompanion.data_item.domain.model.Item
import com.jlahougue.dndcompanion.data_item.presentation.component.ItemCard

@Composable
fun Inventory(
    items: List<Item>,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.inventory).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_item),
                modifier = Modifier
                    .size(40.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            onEvent(ItemEvent.OnItemCreated)
                        },
                    )
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }
        Divider()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(250.dp)
        ) {
            items(
                items,
                key = { item -> item.id }
            ) { item ->
                ItemCard(
                    item = item,
                    onEvent = onEvent,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall)
                )
            }
        }
    }
}

@Preview
@Composable
fun InventoryPreview() {
    DnDCompanionTheme {
        Inventory(
            items = listOf(
                Item(
                    1,
                    1,
                    1,
                    "Epée",
                    "Une épée",
                    1,
                    Currency.GOLD,
                    1f
                ),
                Item(
                    1,
                    2,
                    1,
                    "Dague",
                    "Une dague",
                    1,
                    Currency.GOLD,
                    1f
                )
            ),
            onEvent = {}
        )
    }
}