package com.jlahougue.dndcompanion.data_item.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
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
        Text(
            text = stringResource(id = R.string.inventory).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        )
        Divider()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp)
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