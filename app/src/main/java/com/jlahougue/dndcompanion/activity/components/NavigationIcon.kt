package com.jlahougue.dndcompanion.activity.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun NavigationIcon(
    item: NavigationItem,
    isSelected: Boolean
) {
    BadgedBox(
        badge = {
            if (item.hasNews) Badge()
        }
    ) {
        Image(
            painter = painterResource(id = if (isSelected) item.selectedIcon else item.unselectedIcon),
            contentDescription = item.label,
            modifier = Modifier.size(24.dp)
        )
    }
}