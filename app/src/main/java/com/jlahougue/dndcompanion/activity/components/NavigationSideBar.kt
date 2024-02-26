package com.jlahougue.dndcompanion.activity.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun NavigationSideBar(
    navController: NavHostController,
    items: List<NavigationItem>
) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    NavigationRail {
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