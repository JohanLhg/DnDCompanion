package com.jlahougue.core_domain.util.extension

fun String.toIntOrZero() = toIntOrNull() ?: 0

fun String.toDoubleOrZero() = toDoubleOrNull() ?: 0.0

fun String.isValidEmail(): Boolean {
    if (isBlank()) return false

    if (!contains("@")) return false

    val localPart = substringBeforeLast("@")
    if (localPart.isBlank()) return false

    val domainPart = substringAfterLast("@")
    return domainPart.contains(".") && domainPart.length > 2
}