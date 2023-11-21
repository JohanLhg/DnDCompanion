package com.jlahougue.dndcompanion.core.domain.util.extensions

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

fun Int.feetToMeterString(): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    val roundedFormatter = DecimalFormat("#.5", symbols)
    val decimalFormatter = DecimalFormat("#.#", symbols)
    var meter = this * 0.3048
    meter = roundedFormatter.format(meter).toDouble()
    return decimalFormatter.format(meter)
}