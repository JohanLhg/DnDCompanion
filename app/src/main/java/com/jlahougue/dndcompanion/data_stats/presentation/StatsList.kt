package com.jlahougue.dndcompanion.data_stats.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.CustomOutlinedTextField
import com.jlahougue.core_presentation.components.FramedBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R

@Composable
fun StatsList(
    stats: com.jlahougue.stats_domain.model.StatsView,
    onEvent: (StatsEvent) -> Unit,
    modifier: Modifier = Modifier
) {
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
                Spacer(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                )
                Text(
                    text = stats.initiative.toSignedString(),
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
                Spacer(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                )
                CustomOutlinedTextField(
                    value = stats.armorClass.toString(),
                    onValueChange = {
                        try {
                            onEvent(StatsEvent.OnArmorClassChanged(it.toInt()))
                        } catch (e: Exception) {
                            onEvent(StatsEvent.OnArmorClassChanged(0))
                        }
                    },
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .width(40.dp)
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
                Spacer(
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                )
                CustomOutlinedTextField(
                    value = stats.speed.toString(),
                    onValueChange = {
                        try {
                            onEvent(StatsEvent.OnSpeedChanged(it.toInt()))
                        } catch (e: Exception) {
                            onEvent(StatsEvent.OnSpeedChanged(0))
                        }
                    },
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .width(40.dp)
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
        StatsList(
            stats = com.jlahougue.stats_domain.model.StatsView(
                cid = 1,
                initiative = 2,
                armorClass = 10,
                speed = 30
            ),
            onEvent = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        )
    }
}