package com.jlahougue.dndcompanion.data_character_sheet.data.source.remote

interface CharacterSheetRemoteDataSource {
    fun load(onEvent: (CharacterSheetFirebaseEvent) -> Unit)
}