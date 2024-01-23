package com.jlahougue.dndcompanion.activity.navigation.character_sheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.activity.components.NavigationItem
import com.jlahougue.dndcompanion.activity.components.NavigationSideBar

fun NavGraphBuilder.characterSheetSection(
    route: String
) {
    composable(
        route = route
    ) {
        val navController = rememberNavController()
        /* NEED TO ADD RESPONSIVE LAYOUT */
        val navigationItems = listOf(
            NavigationItem(
                label = stringResource(id = R.string.title_combat_screen),
                route = CharacterSheetScreen.Combat.route,
                unselectedIcon = R.drawable.combat,
                selectedIcon = R.drawable.combat
            ),
            NavigationItem(
                label = stringResource(id = R.string.title_spells_screen),
                route = CharacterSheetScreen.Spells.route,
                unselectedIcon = R.drawable.spell_book,
                selectedIcon = R.drawable.spell_book
            ),
            NavigationItem(
                label = stringResource(id = R.string.title_equipment_screen),
                route = CharacterSheetScreen.Equipment.route,
                unselectedIcon = R.drawable.backpack,
                selectedIcon = R.drawable.backpack
            )
        )
        Row {
            NavigationSideBar(
                navController = navController,
                items = navigationItems
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp)
            )
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier.padding(it)) {
                    CharacterSheetGraph(navController = navController)
                }
            }

        }
    }
}