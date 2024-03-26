package com.jlahougue.dndcompanion.navigation.character_sheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlahougue.core_presentation.util.UiEvent
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.components.NavigationItem
import com.jlahougue.dndcompanion.components.NavigationSideBar
import com.jlahougue.dndcompanion.navigation.character_sheet.components.SettingsDrawer
import kotlinx.coroutines.launch

fun NavGraphBuilder.characterSheetSection(
    route: String,
    navigateBackToAuthentication: () -> Unit,
    navigateBackToCharacterSelection: () -> Unit
) {
    composable(
        route = route
    ) {
        val navController = rememberNavController()
        /* NEED TO ADD RESPONSIVE LAYOUT */
        val navigationItems = listOf(
            NavigationItem(
                label = stringResource(id = R.string.title_profile_screen),
                route = CharacterSheetScreen.Profile.route,
                unselectedIcon = com.jlahougue.core_presentation.R.drawable.profile,
                selectedIcon = com.jlahougue.core_presentation.R.drawable.profile
            ),
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
        val context = LocalContext.current
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        val onUiEvent: (UiEvent) -> Unit = { event ->
            scope.launch {
                when (event) {
                    is UiEvent.ShowError -> snackbarHostState.showSnackbar(
                        event.message.getString(context)
                    )
                }
            }
        }
        ModalNavigationDrawer(
            drawerContent = {
                SettingsDrawer(
                    navigateBackToAuthentication = navigateBackToAuthentication,
                    navigateBackToCharacterSelection = navigateBackToCharacterSelection,
                    onUiEvent = onUiEvent
                )
            },
            drawerState = drawerState
        ) {
            Row {
                NavigationSideBar(
                    navController = navController,
                    items = navigationItems,
                    onSettingsClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isOpen) close() else open()
                            }
                        }
                    }
                )
                VerticalDivider()
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        CharacterSheetGraph(
                            navController = navController,
                            onUiEvent = onUiEvent
                        )
                    }
                }
            }
        }
    }
}