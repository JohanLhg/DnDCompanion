package com.jlahougue.feature.settings_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.user_info_domain.model.Language

@Composable
fun LanguageSelector(
    painter: Painter,
    label: String,
    language: Language,
    selectedLanguage: Language,
    onEvent: (Language) -> Unit,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painter,
        contentDescription = label,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { onEvent(language) }
            )
            .padding(MaterialTheme.spacing.small)
            .alpha(
                if (selectedLanguage == language) 1f
                else 0.5f
            )
    )
}