package com.jlahougue.damage_type_presentation

import com.jlahougue.damage_type_domain.model.DamageType

data class DamageTypeDialogState(
    val isShown: Boolean = false,
    val damageType: DamageType? = null
)
