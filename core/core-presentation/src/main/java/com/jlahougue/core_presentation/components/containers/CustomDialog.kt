package com.jlahougue.core_presentation.components.containers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun CustomDialog(
    isShown: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    headerAlignment: Alignment.Vertical = Alignment.CenterVertically,
    hasHeaderPadding: Boolean = true,
    header: @Composable RowScope.() -> Unit,
    hasContentPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    if (!isShown) return

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties
    ) {
        Card(
            shape = RoundedCornerShape(MaterialTheme.spacing.small),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            modifier = modifier
        ) {
            Row(
                verticalAlignment = headerAlignment,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .padding(
                        if (hasHeaderPadding) MaterialTheme.spacing.medium
                        else 0.dp
                    ),
                content = header
            )
            if (actions != null) {
                Column(
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                if (hasContentPadding) MaterialTheme.spacing.small
                                else 0.dp
                            ),
                        content = content
                    )
                    HorizontalDivider()
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.small)
                            .fillMaxWidth(),
                        content = actions
                    )
                }
            }
            else {
                Column(
                    modifier = Modifier
                        .padding(
                            if (hasContentPadding) MaterialTheme.spacing.small
                            else 0.dp
                        ),
                    content = content
                )
            }
        }
    }
}

@Composable
fun CustomDialog(
    isShown: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    headerAlignment: Alignment.Vertical = Alignment.CenterVertically,
    hasHeaderPadding: Boolean = true,
    header: @Composable RowScope.() -> Unit,
    hasContentPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    CustomDialog(
        isShown = isShown,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
        headerAlignment = headerAlignment,
        hasHeaderPadding = hasHeaderPadding,
        header = header,
        hasContentPadding = hasContentPadding,
        content = content,
        actions = null
    )
}

@Composable
fun CustomDialog(
    isShown: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    title: String,
    hasContentPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    CustomDialog(
        isShown = isShown,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
        header = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        content = content,
        hasContentPadding = hasContentPadding,
        actions = actions
    )
}

@Composable
fun CustomDialog(
    isShown: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    properties: DialogProperties = DialogProperties(),
    title: String,
    hasContentPadding: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    CustomDialog(
        isShown = isShown,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        properties = properties,
        header = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        hasContentPadding = hasContentPadding,
        content = content,
        actions = null
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