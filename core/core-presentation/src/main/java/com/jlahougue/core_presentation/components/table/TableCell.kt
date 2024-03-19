package com.jlahougue.core_presentation.components.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TableCell(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodySmall,
    background: Color = Color.Transparent,
    width: Dp = 0.dp,
    onWidthSet: (Dp) -> Unit = {},
    maxColumnWidth: Dp
) {
    val density = LocalDensity.current
    fun Int.asDp() = density.run {
        this@asDp.toDp()
    }

    Text(
        text = text,
        style = style,
        modifier = Modifier
            .background(background)
            .padding(4.dp)
            .widthIn(width, maxColumnWidth)
            .onSizeChanged { onWidthSet(it.width.asDp()) },
        textAlign = TextAlign.Center
    )
}