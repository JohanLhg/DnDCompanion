package com.jlahougue.profile_presentation

import com.jlahougue.character_domain.model.Character
import com.jlahougue.class_presentation.detail_dialog.ClassDialogState
import com.jlahougue.class_presentation.list_dialog.ClassListDialogState
import com.jlahougue.core_domain.util.LoadImageState

data class ProfileState(
    val character: Character = Character(),
    val image: LoadImageState = LoadImageState(),
    val classListDialog: ClassListDialogState = ClassListDialogState(),
    val classDialog: ClassDialogState = ClassDialogState()
)
