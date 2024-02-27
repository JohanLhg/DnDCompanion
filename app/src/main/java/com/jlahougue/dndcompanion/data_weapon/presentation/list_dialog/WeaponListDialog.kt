package com.jlahougue.dndcompanion.data_weapon.presentation.list_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jlahougue.core_domain.util.extension.asDp
import com.jlahougue.core_presentation.components.CustomSearchBar
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponEvent
import com.jlahougue.dndcompanion.data_weapon.presentation.component.WeaponCard
import com.jlahougue.weapon_domain.model.WeaponInfo

@Composable
fun WeaponListDialog(
    state: WeaponListDialogState,
    onEvent: (WeaponListDialogEvent) -> Unit,
    onWeaponEvent: (WeaponEvent) -> Unit
) {
    if (!state.isShown) return
    val search = state.search
    val weapons = state.weapons
    val unitSystem = state.unitSystem
    Dialog(
        onDismissRequest = {
            onEvent(WeaponListDialogEvent.OnDismiss)
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onEvent(WeaponListDialogEvent.OnDismiss)
                    }
                )
                .padding(MaterialTheme.spacing.extraLarge)
                .clickable(enabled = false) { }
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                ),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Column {
                var searchHeight by remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .onSizeChanged {
                            searchHeight = it.height.asDp(density)
                        }
                ) {
                    CustomSearchBar(
                        value = search,
                        onValueChange = {
                            onEvent(WeaponListDialogEvent.OnSearchChange(it))
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                    VerticalDivider(
                        modifier = Modifier
                            .height(searchHeight)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.weapon_melee),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        colorFilter = ColorFilter.tint(
                            if (state.filter == WeaponListDialogState.Filter.MELEE) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .height(searchHeight)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = false),
                                onClick = {
                                    onEvent(
                                        WeaponListDialogEvent.OnFilterChange(
                                            WeaponListDialogState.Filter.MELEE
                                        )
                                    )
                                },
                            )
                            .padding(MaterialTheme.spacing.small)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.weapon_ranged),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        colorFilter = ColorFilter.tint(
                            if (state.filter == WeaponListDialogState.Filter.RANGED) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .height(searchHeight)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = false),
                                onClick = {
                                    onEvent(
                                        WeaponListDialogEvent.OnFilterChange(
                                            WeaponListDialogState.Filter.RANGED
                                        )
                                    )
                                },
                            )
                            .padding(MaterialTheme.spacing.small)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.magic),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        colorFilter = ColorFilter.tint(
                            if (state.filter == WeaponListDialogState.Filter.MAGIC) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier
                            .height(searchHeight)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple(bounded = false),
                                onClick = {
                                    onEvent(
                                        WeaponListDialogEvent.OnFilterChange(
                                            WeaponListDialogState.Filter.MAGIC
                                        )
                                    )
                                },
                            )
                            .padding(MaterialTheme.spacing.small)
                    )
                }
                HorizontalDivider()
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
                            onEvent = onWeaponEvent,
                            modifier = Modifier
                                .padding(vertical = MaterialTheme.spacing.extraSmall)
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun WeaponListDialogPreview() {
    DnDCompanionTheme {
        WeaponListDialog(
            state = WeaponListDialogState(
                isShown = true,
                weapons = listOf(
                    WeaponInfo(
                        count = 6,
                        name = "Dagger",
                        range = 20,
                        throwRangeMin = 60,
                        throwRangeMax = 200,
                        damage = "1d4",
                        modifier = 2,
                    ),
                    WeaponInfo(
                        count = 1,
                        name = "Longsword",
                        range = 20,
                        damage = "1d8",
                        modifier = -2,
                    )
                ),
                unitSystem = com.jlahougue.settings_domain.model.UnitSystem.METRIC
            ),
            onEvent = {},
            onWeaponEvent = {}
        )
    }
}