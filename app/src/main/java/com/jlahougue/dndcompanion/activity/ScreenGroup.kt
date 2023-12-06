package com.jlahougue.dndcompanion.activity

sealed class ScreenGroup(val route: String) {
    data object Authentication : ScreenGroup("authentication")
    data object CharacterSelection : ScreenGroup("character_selection")
    data object Combat : ScreenGroup("combat")
}