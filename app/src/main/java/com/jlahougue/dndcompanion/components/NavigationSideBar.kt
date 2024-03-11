package com.jlahougue.dndcompanion.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R

@Composable
fun NavigationSideBar(
    navController: NavHostController,
    items: List<NavigationItem>,
    onSettingsClick: () -> Unit
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    NavigationRail(
        header = {
            IconButton(onClick = onSettingsClick) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = stringResource(id = R.string.settings)
                )
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = MaterialTheme.spacing.medium,
                alignment = Alignment.Bottom
            ),
            modifier = Modifier.fillMaxHeight()
        ) {
            items.forEach { item ->
                NavigationRailItem(
                    selected = currentDestination?.route?.let { it == item.route } ?: false,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        NavigationIcon(
                            item = item,
                            isSelected = currentDestination?.route?.let { it == item.route } ?: false
                        )
                    },
                    label = {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                )
            }
        }
    }
}