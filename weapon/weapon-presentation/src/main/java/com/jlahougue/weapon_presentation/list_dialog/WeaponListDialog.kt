package com.jlahougue.weapon_presentation.list_dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.components.text_fileds.CustomSearchBar
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.core_presentation.util.extensions.asDp
import com.jlahougue.weapon_domain.model.WeaponInfo
import com.jlahougue.weapon_presentation.R
import com.jlahougue.weapon_presentation.WeaponEvent
import com.jlahougue.weapon_presentation.component.WeaponCard
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun WeaponListDialog(
    state: WeaponListDialogState,
    onEvent: (WeaponListDialogEvent) -> Unit,
    onWeaponEvent: (WeaponEvent) -> Unit
) {
    val weapons = state.weapons
    val unitSystem = state.unitSystem
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(WeaponListDialogEvent.OnDismiss) },
        hasHeaderPadding = false,
        header = {
            WeaponListDialogHeader(
                state = state,
                onEvent = onEvent
            )
        },
        hasContentPadding = false
    ) {
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

@Composable
fun WeaponListDialogHeader(
    state: WeaponListDialogState,
    onEvent: (WeaponListDialogEvent) -> Unit
) {
    val search = state.search
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
                .weight(1f),
            color = MaterialTheme.colorScheme.onPrimary,
            focusedColor = MaterialTheme.colorScheme.inversePrimary
        )
        VerticalDivider(
            modifier = Modifier
                .height(searchHeight)
        )
        ImageFilter(
            painter = painterResource(id = R.drawable.weapon_melee),
            description = "stringResource(id = R.string.melee_weapon)",
            filter = WeaponListDialogState.Filter.MELEE,
            selectedFilter = state.filter,
            height = searchHeight,
            onEvent = onEvent
        )
        ImageFilter(
            painter = painterResource(id = R.drawable.weapon_ranged),
            description = "stringResource(id = R.string.ranged_weapon)",
            filter = WeaponListDialogState.Filter.RANGED,
            selectedFilter = state.filter,
            height = searchHeight,
            onEvent = onEvent
        )
        ImageFilter(
            painter = painterResource(id = CoreR.drawable.magic),
            description = "stringResource(id = R.string.magic_weapon)",
            filter = WeaponListDialogState.Filter.MAGIC,
            selectedFilter = state.filter,
            height = searchHeight,
            onEvent = onEvent
        )
    }
}

@Composable
fun ImageFilter(
    painter: Painter,
    description: String,
    filter: WeaponListDialogState.Filter,
    selectedFilter: WeaponListDialogState.Filter,
    height: Dp,
    onEvent: (WeaponListDialogEvent) -> Unit
) {
    Image(
        painter = painter,
        contentDescription = description,
        contentScale = ContentScale.FillHeight,
        colorFilter = ColorFilter.tint(
            if (selectedFilter == filter) MaterialTheme.colorScheme.inversePrimary
            else MaterialTheme.colorScheme.onPrimary
        ),
        modifier = Modifier
            .height(height)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false),
                onClick = {
                    onEvent(
                        WeaponListDialogEvent.OnFilterChange(filter)
                    )
                },
            )
            .padding(MaterialTheme.spacing.small)
    )
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
                filter = WeaponListDialogState.Filter.MELEE,
                unitSystem = com.jlahougue.user_info_domain.model.UnitSystem.METRIC
            ),
            onEvent = {},
            onWeaponEvent = {}
        )
    }
}