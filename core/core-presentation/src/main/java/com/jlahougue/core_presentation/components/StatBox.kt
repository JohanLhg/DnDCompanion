package com.jlahougue.core_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.R
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun StatBox(
    label: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
    ) {
        Row {
            var rowH by remember { mutableStateOf(0.dp) }
            val density = LocalDensity.current
            fun Int.asDp() = density.run {
                this@asDp.toDp()
            }
            Image(
                painter = painterResource(id = R.drawable.spell_stats_background_start),
                contentDescription = null,
                modifier = Modifier
                    .height(rowH)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .onSizeChanged {
                        rowH = it.height.asDp()
                    }
                    .weight(1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.spell_stats_background_middle),
                    contentDescription = null,
                    modifier = Modifier
                        .height(rowH)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillBounds
                )
                Box(
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small)
                ) {
                    content()
                }
            }
            Image(
                painter = painterResource(id = R.drawable.spell_stats_background_end),
                contentDescription = null,
                modifier = Modifier
                    .height(rowH)
            )
        }
        Text(
            text = label,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.extraSmall),
            style = textStyle.copy(
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview
@Composable
fun StatBoxPreview() {
    DnDCompanionTheme {
        StatBox(
            label = "SPELL SAVE DC"
        ) {
            Text(
                text = "17",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}