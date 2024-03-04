package com.jlahougue.dndcompanion

sealed class ScreenGroup(val route: String) {
    data object Authentication : ScreenGroup("authentication")
    data object CharacterSelection : ScreenGroup("character_selection")
    data object CharacterSheet : ScreenGroup("character_sheet")
    data object Combat : ScreenGroup("combat")
}