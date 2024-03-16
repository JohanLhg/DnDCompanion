package com.jlahougue.core_presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jlahougue.core_presentation.theme.DnDCompanionTheme

@Composable
fun LinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    lineColor: Color = Color.Black
) {
    val textSize = textStyle.lineHeight.value.sp

    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .width(IntrinsicSize.Max)
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxSize(),
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle
        )
        Canvas(modifier = Modifier.fillMaxSize()) {
            var yCord = 0.8f * textSize.toPx()
            repeat(4000) {
                drawLine(
                    lineColor,
                    Offset(0f, yCord),
                    Offset(size.width, yCord),
                    strokeWidth = 2f
                )
                yCord += textSize.toPx()
            }
        }
    }
}

@Preview
@Composable
private fun LinedTextFieldPreview() {
    DnDCompanionTheme {
        LinedTextField(
            value = "Hello dsfkdsfhds jkfhdks fdsfdd \n fdsufhdsihfidshfids",
            onValueChange = {},
            textStyle = MaterialTheme.typography.labelSmall,
            lineColor = MaterialTheme.colorScheme.primary
        )
    }
}