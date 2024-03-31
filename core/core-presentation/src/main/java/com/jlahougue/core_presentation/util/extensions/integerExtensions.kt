package com.jlahougue.core_presentation.util.extensions

import androidx.compose.ui.unit.Density

fun Int.asDp(density: Density) = density.run {
    this@asDp.toDp()
}