package com.jlahougue.money_presentation.util

import androidx.compose.ui.graphics.Color
import com.jlahougue.money_domain.model.Currency

fun Currency.asColor(): Color {
    return when (this) {
        Currency.COPPER -> Color(0xFFB87333)
        Currency.SILVER -> Color(0xFF5E5E5E)
        Currency.GOLD -> Color(0xFFFFD700)
        Currency.PLATINUM -> Color(0xFFC0C0C0)
    }
}