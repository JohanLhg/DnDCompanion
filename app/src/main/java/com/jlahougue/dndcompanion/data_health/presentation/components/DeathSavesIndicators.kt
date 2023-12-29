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
import com.jlahougue.dndcompanion.core.presentation.theme.Green
import com.jlahougue.dndcompanion.core.presentation.theme.Red
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_health.domain.model.DeathSaves
import com.jlahougue.dndcompanion.data_health.presentation.HealthEvent

@Composable
fun DeathSavesIndicators(
    deathSaves: DeathSaves,
    onEvent: (HealthEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        DividerTitle(
            title = stringResource(R.string.death_saves),
            modifier = Modifier.fillMaxWidth()
        )
        DeathSavesRow(
            name = stringResource(R.string.successes),
            rolls = deathSaves.successes,
            setRolls = {
                onEvent(HealthEvent.OnDeathSavesSuccessChange(it))
            },
            color = Green,
            modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
        )
        DeathSavesRow(
            name = stringResource(R.string.failures),
            rolls = deathSaves.failures,
            setRolls = {
                onEvent(HealthEvent.OnDeathSavesFailureChange(it))
            },
            color = Red
        )
    }
}

@Composable
fun DeathSavesRow(
    name: String,
    rolls: Int,
    setRolls: (Int) -> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {
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
            checked = rolls >= 1,
            colors = CheckboxDefaults.colors(
                uncheckedColor = color,
                checkedColor = color
            ),
            modifier = Modifier
                .height(20.dp)
                .width(25.dp),
            onCheckedChange = {
                if (rolls == 1) setRolls(0)
                else setRolls(1)
            }
        )
        Checkbox(
            checked = rolls >= 2,
            colors = CheckboxDefaults.colors(
                uncheckedColor = color,
                checkedColor = color
            ),
            modifier = Modifier
                .height(20.dp)
                .width(25.dp),
            onCheckedChange = {
                if (rolls == 2) setRolls(1)
                else setRolls(2)
            }
        )
        Checkbox(
            checked = rolls >= 3,
            colors = CheckboxDefaults.colors(
                uncheckedColor = color,
                checkedColor = color
            ),
            modifier = Modifier
                .height(20.dp)
                .width(25.dp),
            onCheckedChange = {
                if (rolls >= 3) setRolls(2)
                else setRolls(3)
            }
        )
    }
}

@Preview(
    showBackground = true,
)
@Composable
fun DeathSavesPreview() {
    DnDCompanionTheme {
        DeathSavesIndicators(
            deathSaves = DeathSaves(successes = 2),
            onEvent = {},
            modifier = Modifier
                .width(200.dp)
        )
    }
}