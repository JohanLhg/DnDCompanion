package com.jlahougue.dndcompanion.data_weapon.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponInfo
import com.jlahougue.dndcompanion.data_weapon.presentation.component.WeaponCard

@Composable
fun WeaponList(
    weapons: List<WeaponInfo>,
    unitSystem: UnitSystem,
    onEvent: (WeaponEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.weapons).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small)
        )
        Divider()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp)
        ) {
            items(
                weapons,
                key = { weapon -> weapon.name }
            ) {
                WeaponCard(
                    weapon = it,
                    unitSystem = unitSystem,
                    onEvent = onEvent,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.extraSmall)
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
fun WeaponItemPreview() {
    DnDCompanionTheme {
        WeaponList(
            getWeaponsPreviewData(),
            UnitSystem.METRIC,
            onEvent = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

private fun getWeaponsPreviewData() : List<WeaponInfo> {
    val weapons = mutableListOf<WeaponInfo>()
    weapons.add(
        WeaponInfo(
            count = 6,
            name = "Dagger",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d4",
            modifier = 2,
        )
    )
    weapons.add(
        WeaponInfo(
            count = 1,
            name = "Longsword",
            range = 20,
            damage = "1d8",
            modifier = -2,
        )
    )
    weapons.add(
        WeaponInfo(
            count = 1,
            name = "Longbow",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d8",
            modifier = 2,
        )
    )
    weapons.add(
        WeaponInfo(
            count = 1,
            name = "Longbow",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d8",
            modifier = 2,
        )
    )
    weapons.add(
        WeaponInfo(
            count = 1,
            name = "Longbow",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d8",
            modifier = 2,
        )
    )
    return weapons
}