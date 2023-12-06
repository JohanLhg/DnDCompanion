package com.jlahougue.dndcompanion.activity.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jlahougue.dndcompanion.feature_combat.presentation.CombatScreen

fun NavGraphBuilder.combatSection(
    route: String,
    navigateToNext: () -> Unit = { }
) {
    composable(
        route = route
    ) {
        CombatScreen(
            abilities = listOf(),
        )
    }
}