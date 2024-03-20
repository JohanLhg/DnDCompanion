package com.jlahougue.core_presentation.components.table

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme

@Composable
fun DataTableCard(
    headers: List<String>,
    rows: List<List<String>>,
    modifier: Modifier = Modifier,
    headerColor: Color = MaterialTheme.colorScheme.primary,
    minColumnWidth: Dp = 20.dp,
    maxColumnWidth: Dp = 75.dp
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        DataTable(
            headers = headers,
            rows = rows,
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            headerColor = headerColor,
            minColumnWidth = minColumnWidth,
            maxColumnWidth = maxColumnWidth
        )
    }
}

@Preview
@Composable
private fun DataTableCardPreview() {
    DnDCompanionTheme {
        DataTableCard(
            headers = listOf("Header 1", "Header 2", "Header 3", "Header 4", "Header 5"),
            rows = listOf(
                listOf("Row 1", "Row 1", "Row 1", "Row 1"),
                listOf("Row 2", "Row 2", "Row 2", "Row 2"),
                listOf("Row 3", "Row 3", "", "", "Row 3"),
            )
        )
    }
}