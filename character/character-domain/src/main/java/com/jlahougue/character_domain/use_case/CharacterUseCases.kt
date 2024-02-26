package com.jlahougue.character_domain.use_case

data class CharacterUseCases(
    val getCharacters: GetCharacters,
    val loadCharacterImage: LoadCharacterImage,
    val getCharacterClass: GetCharacterClass
)