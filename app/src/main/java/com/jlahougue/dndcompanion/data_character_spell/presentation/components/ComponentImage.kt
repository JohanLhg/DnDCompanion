package com.jlahougue.dndcompanion.data_character_spell.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun ComponentImage(
    painter: Painter,
    contentDescription: String
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
            .size(28.dp)
            .padding(MaterialTheme.spacing.extraSmall)
    )
}