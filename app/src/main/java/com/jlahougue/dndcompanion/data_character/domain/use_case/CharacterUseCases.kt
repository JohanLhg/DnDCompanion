package com.jlahougue.dndcompanion.data_character.domain.use_case

data class CharacterUseCases(
    val createCharacter: CreateCharacter,
    val deleteCharacter: DeleteCharacter,
    val getCharacters: GetCharacters,
    val loadCharacterImage: LoadCharacterImage,
    val getCharacterClass: GetCharacterClass
)