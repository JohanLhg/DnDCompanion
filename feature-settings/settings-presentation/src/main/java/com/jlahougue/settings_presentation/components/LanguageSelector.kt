package com.jlahougue.settings_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.settings_domain.model.Language

@Composable
fun LanguageSelector(
    painter: Painter,
    label: String,
    language: Language,
    selectedLanguage: Language,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painter,
        contentDescription = label,
        modifier = modifier
            .padding(MaterialTheme.spacing.small)
            .alpha(
                if (selectedLanguage == language) 1f
                else 0.5f
            )
    )
}