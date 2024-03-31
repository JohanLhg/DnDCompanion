package com.jlahougue.class_presentation.list_dialog

import com.jlahougue.class_domain.model.Class

data class ClassListDialogState(
    val isShown: Boolean = false,
    val classes: List<Class> = emptyList(),
    val selectedClass: Class? = null
)
