package com.jlahougue.profile_presentation

import com.jlahougue.character_domain.model.Character
import com.jlahougue.class_presentation.detail_dialog.ClassDialogState
import com.jlahougue.class_presentation.list_dialog.ClassListDialogState

data class ProfileState(
    val character: Character = Character(),
    val imageUri: String? = null,
    val classListDialog: ClassListDialogState = ClassListDialogState(),
    val classDialog: ClassDialogState = ClassDialogState()
)
