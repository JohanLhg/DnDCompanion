package com.jlahougue.item_presentation

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
import androidx.compose.material3.HorizontalDivider
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
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.item_domain.model.Item
import com.jlahougue.item_presentation.component.ItemCard
import com.jlahougue.item_presentation.dialog.ItemDialog
import com.jlahougue.item_presentation.dialog.ItemDialogEvent
import com.jlahougue.money_domain.model.Currency

@Composable
fun Inventory(
    state: InventoryState,
    onEvent: (ItemEvent) -> Unit,
    onDialogEvent: (ItemDialogEvent) -> Unit,
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
        HorizontalDivider()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(250.dp)
        ) {
            items(
                state.items,
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
    ItemDialog(
        state = state.dialog,
        onEvent = onDialogEvent
    )
}

@Preview
@Composable
fun InventoryPreview() {
    DnDCompanionTheme {
        Inventory(
            InventoryState(
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
                )
            ),
            onEvent = {},
            onDialogEvent = {}
        )
    }
}