package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing

@Composable
fun DeathSaves(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        DividerTitle(
            title = stringResource(R.string.death_saves),
            modifier = Modifier.fillMaxWidth()
        )
        DeathSavesRow(
            name = stringResource(R.string.successes),
            color = Color(0xFF3E9400),
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        )
        DeathSavesRow(
            name = stringResource(R.string.failures),
            color = Color(0xFFBC0606)
        )
    }
}

@Composable
fun DeathSavesRow(name: String, color: Color, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier
            .width(MaterialTheme.spacing.medium)
            .height(0.dp)
            .weight(1f))
        Checkbox(
            checked = false,
            colors = CheckboxDefaults.colors(
                uncheckedColor = color,
                checkedColor = color
            ),
            modifier = Modifier
                .height(20.dp)
                .width(25.dp),
            onCheckedChange = {}
        )
        Checkbox(
            checked = false,
            colors = CheckboxDefaults.colors(
                uncheckedColor = color,
                checkedColor = color
            ),
            modifier = Modifier
                .height(20.dp)
                .width(25.dp),
            onCheckedChange = {}
        )
        Checkbox(
            checked = false,
            colors = CheckboxDefaults.colors(
                uncheckedColor = color,
                checkedColor = color
            ),
            modifier = Modifier
                .height(20.dp)
                .width(25.dp),
            onCheckedChange = {}
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun DeathSavesPreview() {
    DnDCompanionTheme {
        DeathSaves()
    }
}