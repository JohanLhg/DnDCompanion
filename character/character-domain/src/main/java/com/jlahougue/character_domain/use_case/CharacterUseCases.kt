package com.jlahougue.character_domain.use_case

data class CharacterUseCases(
    val getCharacters: GetCharacters,
    val getCharacter: GetCharacter,
    val updateCharacter: UpdateCharacter,
    val loadCharacterImage: LoadCharacterImage,
    val getCharacterClass: GetCharacterClass
)