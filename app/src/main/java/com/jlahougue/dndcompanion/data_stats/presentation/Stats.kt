package com.jlahougue.dndcompanion.data_stats.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.components.FramedBox
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing

@Composable
fun Stats(modifier: Modifier = Modifier) {
    FramedBox(
        title = stringResource(id = R.string.stats),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.small)
            ) {
                Text(
                    text = stringResource(id = R.string.stats_initiative),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                )
                Spacer(modifier = Modifier
                    .width(0.dp)
                    .weight(1f))
                Text(
                    text = "+2",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(40.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.small)
            ) {
                Text(
                    text = stringResource(id = R.string.stats_armor_class),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                )
                Spacer(modifier = Modifier
                    .width(0.dp)
                    .weight(1f))
                BasicTextField(
                    value = "0",
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .width(40.dp)
                        .border(
                            1.dp,
                            Color.Gray,
                            RoundedCornerShape(5.dp)
                        )
                        .padding(horizontal = MaterialTheme.spacing.small)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.stats_speed),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                )
                Spacer(modifier = Modifier
                    .width(0.dp)
                    .weight(1f))
                BasicTextField(
                    value = "0",
                    onValueChange = {},
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .width(40.dp)
                        .border(
                            1.dp,
                            Color.Gray,
                            RoundedCornerShape(5.dp)
                        )
                        .padding(horizontal = MaterialTheme.spacing.small)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun StatsPreview() {
    DnDCompanionTheme {
        Stats(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        )
    }
}