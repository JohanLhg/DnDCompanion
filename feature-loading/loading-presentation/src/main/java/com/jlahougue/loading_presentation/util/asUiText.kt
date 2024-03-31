package com.jlahougue.loading_presentation.util

import com.jlahougue.core_presentation.util.UiText
import com.jlahougue.loading_domain.util.LoaderKey
import com.jlahougue.loading_presentation.R

fun LoaderKey.asUiText() = when (this) {
    LoaderKey.ALL -> UiText.StringResource(R.string.loading)
    LoaderKey.CLASSES -> UiText.StringResource(R.string.loading_classes)
    LoaderKey.DAMAGE_TYPES -> UiText.StringResource(R.string.loading_damage_types)
    LoaderKey.SPELLS -> UiText.StringResource(R.string.loading_spells)
    LoaderKey.PROPERTIES -> UiText.StringResource(R.string.loading_weapon_properties)
    LoaderKey.WEAPONS -> UiText.StringResource(R.string.loading_weapons)
    LoaderKey.CHARACTERS -> UiText.StringResource(R.string.loading_characters)
}