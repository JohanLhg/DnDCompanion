package com.jlahougue.dndcompanion.data_currency.domain.model

import android.content.Context
import androidx.compose.runtime.Composable
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText

enum class Currency(
    val label: UiText,
    val shortLabel: UiText,
    val color: Long
) {
    COPPER(
        UiText.StringResource(R.string.copper_pieces),
        UiText.StringResource(R.string.copper_pieces_short),
        0xFFB87333
    ) {
        override fun toDisplayedValue(value: Int): Int {
            return value % 10
        }
    },
    SILVER(
        UiText.StringResource(R.string.silver_pieces),
        UiText.StringResource(R.string.silver_pieces_short),
        0xFF5E5E5E
    ) {
        override fun toDisplayedValue(value: Int): Int {
            return (value % 100).floorDiv(10)
        }
    },
    GOLD(
        UiText.StringResource(R.string.gold_pieces),
        UiText.StringResource(R.string.gold_pieces_short),
        0xFFFFD700
    ) {
        override fun toDisplayedValue(value: Int): Int {
            return (value % 1000).floorDiv(100)
        }
    },
    PLATINUM(
        UiText.StringResource(R.string.platinum_pieces),
        UiText.StringResource(R.string.platinum_pieces_short),
        0xFFC0C0C0
    ) {
        override fun toDisplayedValue(value: Int): Int {
            return value.floorDiv(1000)
        }
    };

    open fun toDisplayedValue(value: Int) = value

    @Composable
    fun getString(value: Int) = "${toDisplayedValue(value)} ${shortLabel.getString()}"

    fun getString(context: Context, value: Int) = "${toDisplayedValue(value)} ${shortLabel.getString(context)}"

    companion object {
        fun from(findValue: String) =
            entries.find { it.name.equals(findValue, true) } ?: COPPER
    }
}