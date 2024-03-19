package com.jlahougue.core_presentation.components.table

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataTable(
    headers: List<String>,
    modifier: Modifier = Modifier,
    rows: List<List<String>> = emptyList(),
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
        val columnWidths = remember { mutableStateMapOf<Int, Dp>() }
        val onWidthSet: (Int, Dp) -> Unit = { index, width ->
            if (!columnWidths.containsKey(index)) {
                columnWidths[index] = width
            }
            else if (columnWidths[index]!! < width) {
                columnWidths[index] = width
            }
        }
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            LazyColumn {
                stickyHeader {
                    Row(
                        modifier = Modifier.background(MaterialTheme.colorScheme.primary)
                    ) {
                        headers.forEachIndexed { index, title ->
                            TableCell(
                                text = title,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    color = MaterialTheme.colorScheme.onPrimary
                                ),
                                width = columnWidths[index] ?: minColumnWidth,
                                onWidthSet = { width ->
                                    onWidthSet(index, width)
                                },
                                maxColumnWidth = maxColumnWidth
                            )
                        }
                    }
                }
                items(rows.size) { index ->
                    val row = rows[index]
                    Row {
                        for (i in headers.indices) {
                            TableCell(
                                text = if (i < row.size) row[i] else "",
                                background = if (index % 2 == 0) MaterialTheme.colorScheme.surface
                                else MaterialTheme.colorScheme.surfaceVariant,
                                width = columnWidths[i] ?: 0.dp,
                                onWidthSet = { width ->
                                    onWidthSet(i, width)
                                },
                                maxColumnWidth = maxColumnWidth
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
private fun DataTablePreview() {
    DnDCompanionTheme {
        DataTable(
            headers = listOf("Header 1", "Header 2", "Header 3", "Header 4", "5"),
            rows = listOf(
                listOf("Row 1", "Row 1", "Row 1", "5"),
                listOf("Row 2", "-- Wider Row 2 --", "Row 2", "Row 2"),
                listOf("Row 3", "Row 3")
            ),
            maxColumnWidth = 200.dp
        )
    }
}