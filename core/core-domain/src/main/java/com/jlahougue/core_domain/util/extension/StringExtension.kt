package com.jlahougue.core_domain.util.extension

fun String.toIntOrZero() = toIntOrNull() ?: 0

fun String.toDoubleOrZero() = toDoubleOrNull() ?: 0.0