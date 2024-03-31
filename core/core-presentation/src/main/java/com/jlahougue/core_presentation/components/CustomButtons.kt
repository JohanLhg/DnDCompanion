package com.jlahougue.core_presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.R
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun ActionButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    CustomButton(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .padding(MaterialTheme.spacing.small),
        enabled = enabled,
        contentPadding = PaddingValues(MaterialTheme.spacing.extraSmall),
        border = border,
        colors = colors
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
        }
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
        )
    }
}

@Composable
fun SecondaryButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    ActionButton(
        label = label,
        onClick = onClick,
        modifier = modifier,
        icon = icon,
        enabled = enabled,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        )
    )
}

@Composable
fun MenuButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    CustomButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        enabled = enabled
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.extraSmall)
                        .align(Alignment.CenterStart)
                )
            }
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.large)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
fun PrimaryMenuButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true
) {
    MenuButton(
        label = label,
        onClick = onClick,
        modifier = modifier
            .padding(
                vertical = MaterialTheme.spacing.extraSmall,
                horizontal = MaterialTheme.spacing.medium
            ),
        icon = icon,
        enabled = enabled
    )
}

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.small),
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = OutlinedTextFieldDefaults.shape,
        contentPadding = contentPadding,
        border = border,
        colors = colors,
        content = content
    )
}

@Composable
fun ConfirmButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    ActionButton(
        label = stringResource(id = R.string.confirm),
        onClick = onClick,
        modifier = modifier,
        icon = Icons.Filled.Check,
        enabled = enabled
    )
}

@Composable
fun CancelButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    SecondaryButton(
        label = stringResource(id = R.string.cancel),
        onClick = onClick,
        modifier = modifier,
        icon = Icons.Filled.Close,
        enabled = enabled
    )
}

@Preview
@Composable
private fun ConfirmButtonPreview() {
    DnDCompanionTheme {
        ConfirmButton(onClick = {})
    }
}

@Preview
@Composable
private fun CancelButtonPreview() {
    DnDCompanionTheme {
        CancelButton(onClick = {})
    }
}

@Preview
@Composable
private fun MenuButtonPreview() {
    DnDCompanionTheme {
        PrimaryMenuButton(
            label = "Menu",
            onClick = {},
            icon = Icons.Filled.Menu
        )
    }
}