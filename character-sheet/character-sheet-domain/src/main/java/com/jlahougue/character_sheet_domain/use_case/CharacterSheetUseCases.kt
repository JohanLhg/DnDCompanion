package com.jlahougue.character_sheet_domain.use_case

data class CharacterSheetUseCases(
    val createCharacter: CreateCharacter,
    val deleteCharacter: DeleteCharacter,
    val clearCharacters: ClearCharacters
)
