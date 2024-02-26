package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_domain.util.extension.asDp
import com.jlahougue.core_presentation.components.CustomOutlinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.Green
import com.jlahougue.core_presentation.theme.Red
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R

@Composable
fun TextFieldWithIncrements(
    value: String,
    onValueChange: (String) -> Unit,
    onPlusClick: () -> Unit,
    plusDescription: String,
    onMinusClick: () -> Unit,
    minusDescription: String,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        var rowH by remember { mutableStateOf(0.dp) }
        val density = LocalDensity.current
        CustomImageButton(
            resourceId = R.drawable.arrow_left,
            description = minusDescription,
            color = Red,
            modifier = Modifier
                .height(rowH)
                .padding(end = MaterialTheme.spacing.extraSmall),
            onClick = {
                focusManager.clearFocus()
                onMinusClick()
            }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Min)
        ) {
            CustomOutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .onSizeChanged {
                        rowH = it.height.asDp(density)
                    }
                    .width(75.dp)
                    .padding(horizontal = MaterialTheme.spacing.small),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    textAlign = TextAlign.Center
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }
        CustomImageButton(
            resourceId = R.drawable.arrow_right,
            description = plusDescription,
            color = Green,
            modifier = Modifier
                .height(rowH)
                .padding(start = MaterialTheme.spacing.extraSmall),
            onClick = {
                focusManager.clearFocus()
                onPlusClick()
            }
        )
    }
}

@Composable
private fun CustomImageButton(
    resourceId: Int,
    description: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = resourceId),
        colorFilter = ColorFilter.tint(color),
        contentDescription = description,
        modifier = modifier
            .width(50.dp)
            .border(
                1.dp,
                color,
                MaterialTheme.shapes.extraSmall
            )
            .clip(MaterialTheme.shapes.extraSmall)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = true,
                    color = color
                ),
                onClick = onClick,
            )
            .padding(MaterialTheme.spacing.extraSmall)
    )
}

@Preview(
    showBackground = true
)
@Composable
fun TextFieldWithIncrementsPreview() {
    DnDCompanionTheme {
        TextFieldWithIncrements(
            value = "999",
            onValueChange = {},
            onPlusClick = {},
            plusDescription = "plus",
            onMinusClick = {},
            minusDescription = "minus",
            modifier = Modifier
                .width(IntrinsicSize.Min)
        )
    }
}