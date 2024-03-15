package com.jlahougue.spells_presentation.components

sealed class SpellSearchEvent {
    data object OnModeChanged : SpellSearchEvent()
    data class OnSearchChanged(val search: String) : SpellSearchEvent()
    data class OnClassFilterClicked(val clazz: String) : SpellSearchEvent()
    data class OnLevelSelected(val level: Int) : SpellSearchEvent()
}