package com.jlahougue.class_presentation.detail_dialog

import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevelStats

data class ClassDialogState(
    val isShown: Boolean = false,
    val clazz: Class? = null,
    val levels: List<ClassLevelStats> = emptyList()
)
