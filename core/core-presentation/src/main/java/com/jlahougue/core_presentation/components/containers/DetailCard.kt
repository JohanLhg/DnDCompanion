package com.jlahougue.core_presentation.components.containers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun DetailCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    headerAlignment: Alignment.Vertical = Alignment.CenterVertically,
    header: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = headerAlignment,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.small)
        ) {
            header()
        }
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
        )
        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
        ) {
            content()
        }
    }
}

@Composable
fun DetailCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    DetailCard(
        onClick = onClick,
        modifier = modifier,
        header = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(vertical = MaterialTheme.spacing.small)
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
            )
        },
        content = content
    )
}