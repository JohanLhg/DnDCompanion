package com.jlahougue.profile_presentation

import com.jlahougue.character_domain.model.Character
import com.jlahougue.character_domain.use_case.LoadImageState

data class ProfileState(
    val character: Character = Character(),
    val image: LoadImageState = LoadImageState()
)
