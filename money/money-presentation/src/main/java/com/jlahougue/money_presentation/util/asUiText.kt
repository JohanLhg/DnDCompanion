package com.jlahougue.money_presentation.util

import androidx.compose.runtime.Composable
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.money_domain.model.Currency
import com.jlahougue.money_presentation.R

fun Currency.asUiText(): UiText {
    return when (this) {
        Currency.COPPER -> UiText.StringResource(R.string.copper_pieces)
        Currency.SILVER -> UiText.StringResource(R.string.silver_pieces)
        Currency.GOLD -> UiText.StringResource(R.string.gold_pieces)
        Currency.PLATINUM -> UiText.StringResource(R.string.platinum_pieces)
    }
}

fun Currency.asShortUiText(): UiText {
    return when (this) {
        Currency.COPPER -> UiText.StringResource(R.string.copper_pieces_short)
        Currency.SILVER -> UiText.StringResource(R.string.silver_pieces_short)
        Currency.GOLD -> UiText.StringResource(R.string.gold_pieces_short)
        Currency.PLATINUM -> UiText.StringResource(R.string.platinum_pieces_short)
    }
}

@Composable
fun Currency.asValuedString(value: Int): String {
    return "${toDisplayedValue(value)} ${asShortUiText().getString()}"
}