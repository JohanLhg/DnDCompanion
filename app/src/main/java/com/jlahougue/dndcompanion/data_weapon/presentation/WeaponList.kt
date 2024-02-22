package com.jlahougue.dndcompanion.data_weapon.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialog
import com.jlahougue.dndcompanion.data_weapon.presentation.dialog.WeaponDialogEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialog
import com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog.WeaponListDialogEvent

@Composable
fun WeaponList(
    unitSystem: UnitSystem,
    state: WeaponState,
    onEvent: (WeaponEvent) -> Unit,
    onListDialogEvent: (WeaponListDialogEvent) -> Unit,
    onDialogEvent: (WeaponDialogEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.weapons).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_item),
                modifier = Modifier
                    .size(40.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            onEvent(WeaponEvent.OnAddWeaponClicked)
                        },
                    )
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }
        Divider()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp)
        ) {
            items(
                state.weapons,
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
    WeaponListDialog(
        state = state.listDialog,
        onEvent = onListDialogEvent,
        onWeaponEvent = onEvent
    )
    WeaponDialog(
        state = state.dialog,
        onEvent = onDialogEvent
    )
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun WeaponItemPreview() {
    DnDCompanionTheme {
        WeaponList(
            UnitSystem.METRIC,
            state = WeaponState(weapons = getWeaponsPreviewData()),
            onEvent = {},
            onListDialogEvent = {},
            onDialogEvent = {},
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