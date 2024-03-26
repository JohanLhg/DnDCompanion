package com.jlahougue.core_presentation.util

import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_presentation.R

fun LoadImageError.asUiText() = when (this) {
    LoadImageError.NO_INTERNET -> UiText.StringResource(R.string.no_internet_connection)
    LoadImageError.NOT_AUTHENTICATED -> UiText.StringResource(R.string.not_authenticated)
    LoadImageError.NOT_AUTHORIZED -> UiText.StringResource(R.string.not_authorized)
    LoadImageError.INVALID_URL -> UiText.StringResource(R.string.invalid_url)
    LoadImageError.NO_IMAGE -> UiText.StringResource(R.string.no_image)
    LoadImageError.CANCELLED -> UiText.StringResource(R.string.load_image_cancelled)
    LoadImageError.UNKNOWN -> UiText.StringResource(R.string.load_image_unknown_error)
}