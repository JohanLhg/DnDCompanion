package com.jlahougue.item_presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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
        HorizontalDivider(
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