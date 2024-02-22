package com.jlahougue.dndcompanion.core.domain.util

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val value: Int
    ) : UiText()

    @Composable
    fun getString(vararg args: Any): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(value, *args)
        }
    }

    fun getString(context: Context, vararg args: Any): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(value, *args)
        }
    }
}