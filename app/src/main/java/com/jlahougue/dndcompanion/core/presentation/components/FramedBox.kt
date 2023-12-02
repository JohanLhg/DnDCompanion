package com.jlahougue.dndcompanion.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import java.util.Locale

@Composable
fun FramedBox(
    modifier: Modifier = Modifier,
    title: String = "",
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp)
                .border(
                    2.dp,
                    Color.Black,
                    RoundedCornerShape(5.dp)
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 7.dp,
                    end = 7.dp,
                    bottom = 7.dp
                )
        ) {
            Text(
                text = title.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(Color.White)
                    .padding(horizontal = 15.dp)
            )
            content()
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun FramedBoxPreview() {
    DnDCompanionTheme {
        FramedBox(
            title = "Test",
            modifier = Modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Max)
                .padding(5.dp)
        ) {
            Text(text = "Test")
        }
    }
}