package com.jlahougue.dndcompanion.navigation.character_sheet

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jlahougue.dndcompanion.ScreenGroup
import com.jlahougue.dndcompanion.navigation.character_sheet.screens.combatSection
import com.jlahougue.dndcompanion.navigation.character_sheet.screens.equipmentSection
import com.jlahougue.dndcompanion.navigation.character_sheet.screens.spellsSection

@Composable
fun CharacterSheetGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = ScreenGroup.CharacterSheet.route,
        startDestination = CharacterSheetScreen.Combat.route,
    ) {
        combatSection(route = CharacterSheetScreen.Combat.route)
        spellsSection(route = CharacterSheetScreen.Spells.route)
        equipmentSection(route = CharacterSheetScreen.Equipment.route)
    }
}

sealed class CharacterSheetScreen(val route: String) {
    data object Combat : CharacterSheetScreen("combat")
    data object Spells : CharacterSheetScreen("spells")
    data object Equipment : CharacterSheetScreen("equipment")
}