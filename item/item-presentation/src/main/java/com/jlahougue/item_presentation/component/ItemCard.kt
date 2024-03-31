package com.jlahougue.item_presentation.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.components.containers.DetailCard
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.item_domain.model.Item
import com.jlahougue.item_presentation.ItemEvent
import com.jlahougue.money_domain.model.Currency

@Composable
fun ItemCard(
    item: Item,
    onEvent: (ItemEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    DetailCard(
        onClick = { onEvent(ItemEvent.OnItemClicked(item.id)) },
        modifier = modifier,
        header = {
            ItemCardHeader(item = item)
        }
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

@Composable
fun RowScope.ItemCardHeader(item: Item) {
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
                currency = Currency.GOLD,
                weight = 1f
            ),
            onEvent = {}
        )
    }
}