package com.jlahougue.property_presentation

import com.jlahougue.property_domain.model.Property

data class PropertyDialogState(
    val isShown: Boolean = false,
    val property: Property? = null
)
