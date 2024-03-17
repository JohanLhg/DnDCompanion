package com.jlahougue.class_presentation

import com.jlahougue.class_domain.model.Class

data class ClassDialogState(
    val isShown: Boolean = false,
    val clazz: Class? = null
)
