package com.jlahougue.class_presentation.list_dialog

import com.jlahougue.class_domain.model.Class

sealed class ClassListDialogEvent {
    data object OnDismiss : ClassListDialogEvent()
    data class OnClassSelected(val clazz: Class) : ClassListDialogEvent()
    data class OnClassDetail(val clazz: Class) : ClassListDialogEvent()
    data object OnConfirm : ClassListDialogEvent()
}