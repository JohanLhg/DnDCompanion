package com.jlahougue.dndcompanion.data_weapon.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.extension.toSignedString
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.core.presentation.theme.spacing
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_weapon.domain.model.WeaponView

@Composable
fun WeaponList(
    weapons: List<WeaponView>,
    unitSystem: UnitSystem,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(300.dp),
        modifier = modifier
    ) {
        items(weapons) {
            WeaponItem(
                weapon = it,
                unitSystem = unitSystem,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeaponItem(
    weapon: WeaponView,
    unitSystem: UnitSystem,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = modifier
            .width(IntrinsicSize.Max)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = stringResource(R.string.amount, weapon.count),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
            Text(
                text = weapon.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
            Text(
                text = stringResource(id = R.string.parenthesis, weapon.getRangeString(unitSystem)),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.weapon_test, weapon.modifier.toSignedString()),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )
            Divider(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.extraSmall)
                    .width(1.dp)
                    .fillMaxHeight()
            )
            Text(
                text = weapon.damage,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(MaterialTheme.spacing.extraSmall)
            )

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
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

private fun getWeaponsPreviewData() : List<WeaponView> {
    val weapons = mutableListOf<WeaponView>()
    weapons.add(
        WeaponView(
            count = 6,
            name = "Dagger",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d4 Slashing",
            modifier = 2,
        )
    )
    weapons.add(
        WeaponView(
            count = 1,
            name = "Longsword",
            range = 20,
            damage = "1d8 Slashing",
            modifier = -2,
        )
    )
    weapons.add(
        WeaponView(
            count = 1,
            name = "Longbow",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d8 Slashing",
            modifier = 2,
        )
    )
    weapons.add(
        WeaponView(
            count = 1,
            name = "Longbow",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d8 Slashing",
            modifier = 2,
        )
    )
    weapons.add(
        WeaponView(
            count = 1,
            name = "Longbow",
            range = 20,
            throwRangeMin = 60,
            throwRangeMax = 200,
            damage = "1d8 Slashing",
            modifier = 2,
        )
    )
    return weapons
}