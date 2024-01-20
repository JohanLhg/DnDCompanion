package com.jlahougue.dndcompanion.data_currency.domain.model

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText

enum class Currency(
    val label: UiText,
    val shortLabel: UiText
) {
    COPPER(
        UiText.StringResource(R.string.copper_pieces),
        UiText.StringResource(R.string.copper_pieces_short)
    ),
    SILVER(
        UiText.StringResource(R.string.silver_pieces),
        UiText.StringResource(R.string.silver_pieces_short)
    ),
    GOLD(
        UiText.StringResource(R.string.gold_pieces),
        UiText.StringResource(R.string.gold_pieces_short)
    ),
    PLATINUM(
        UiText.StringResource(R.string.platinum_pieces),
        UiText.StringResource(R.string.platinum_pieces_short)
    );

    companion object {
        fun from(findValue: String) =
            entries.find { it.name.equals(findValue, true) } ?: COPPER
    }
}