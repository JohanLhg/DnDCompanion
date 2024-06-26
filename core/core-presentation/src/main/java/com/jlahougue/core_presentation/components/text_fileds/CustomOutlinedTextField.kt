package com.jlahougue.core_presentation.components.text_fileds

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colorScheme.secondary,
    horizontalPadding: Dp = MaterialTheme.spacing.small,
    verticalPadding: Dp = 0.dp,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = 1
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        textFieldValue =
            if (isFocused) {
                textFieldValue.copy(
                    selection = TextRange(0, textFieldValue.text.length)
                )
            } else {
                textFieldValue.copy(
                    text = value,
                    selection = TextRange.Zero
                )
            }
    }

    if (!isFocused) {
        textFieldValue = textFieldValue.copy(
            text = value,
            selection = TextRange.Zero
        )
    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onValueChange(it.text)
        },
        modifier = modifier
            .border(
                1.dp,
                borderColor,
                MaterialTheme.shapes.extraSmall
            )
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            ),
        textStyle = textStyle,
        interactionSource = interactionSource,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines
    )
}

@Preview
@Composable
fun CustomOutlinedTextFieldPreview() {
    DnDCompanionTheme {
        CustomOutlinedTextField(
            value = "10",
            onValueChange = {},
            modifier = Modifier
                .width(75.dp)
                .padding(horizontal = MaterialTheme.spacing.small),
            textStyle = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.Center
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
    }
}