package com.jlahougue.dndcompanion.activity.components

data class NavigationItem(
    val label: String,
    val route: String,
    val unselectedIcon: Int,
    val selectedIcon: Int,
    val hasNews: Boolean = false
)
