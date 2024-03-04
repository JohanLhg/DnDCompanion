package com.jlahougue.core_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun <T> ListOfLinkedItems(
    title: String,
    items: List<T>,
    itemToString: (T) -> String,
    onEvent: (T) -> Unit,
) {
    if (items.isNotEmpty()) {
        Row(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .padding(end = MaterialTheme.spacing.extraSmall)
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
            ) {
                itemsIndexed(
                    items = items,
                    key = { _, item -> itemToString(item) }
                ) { index, item ->
                    Text(
                        text = itemToString(item),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .clickable {
                                onEvent(item)
                            }
                    )
                    if (index < items.size - 1) {
                        Text(
                            text = ",",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}