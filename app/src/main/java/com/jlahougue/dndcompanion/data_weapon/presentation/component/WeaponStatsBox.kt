package com.jlahougue.dndcompanion.data_weapon.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.StatBox
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R
import com.jlahougue.weapon_domain.model.WeaponStats

@Composable
fun WeaponStatsBox(
    stats: WeaponStats,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.width(IntrinsicSize.Max)
        ) {
            StatBox(
                label = stringResource(id = R.string.proficiency_bonus),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = MaterialTheme.spacing.small)
            ) {
                Text(
                    text = stats.proficiencyBonus.toSignedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.small),
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
            StatBox(
                label = stringResource(id = R.string.test_melee),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = MaterialTheme.spacing.small)
            ) {
                Text(
                    text = stats.strength.toSignedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.small),
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
                )
            }
            StatBox(
                label = stringResource(id = R.string.test_ranged),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = MaterialTheme.spacing.small)
            ) {
                Text(
                    text = stats.dexterity.toSignedString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.small),
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center
                    )
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
fun WeaponStatsBoxPreview() {
    DnDCompanionTheme {
        WeaponStatsBox(
            stats = WeaponStats(
                proficiencyBonus = 2,
                strength = 3,
                dexterity = 4
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small)
        )
    }
}