package com.jlahougue.core_presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun CustomDialog(
    isShown: Boolean,
    onDismissRequest: () -> Unit,
    titleContent: @Composable RowScope.() -> Unit,
    hasContentPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    if (!isShown) return

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = RoundedCornerShape(MaterialTheme.spacing.medium),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    titleContent()
                }
                Column(
                    modifier = Modifier
                        .padding(
                            if (hasContentPadding) MaterialTheme.spacing.small
                            else 0.dp
                        )
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun CustomDialog(
    isShown: Boolean,
    onDismissRequest: () -> Unit,
    title: String,
    hasContentPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    CustomDialog(
        isShown = isShown,
        onDismissRequest = onDismissRequest,
        titleContent = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        content = content,
        hasContentPadding = hasContentPadding
    )
}

@Preview
@Composable
private fun CustomDialogPreview() {
    DnDCompanionTheme {
        CustomDialog(
            isShown = true,
            onDismissRequest = {},
            title = "My Custom Dialog",
            content = {
                // Content
            }
        )
    }
}