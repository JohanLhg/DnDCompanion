package com.jlahougue.dndcompanion.feature_spells.di

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character.domain.use_case.CharacterUseCases
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases
import com.jlahougue.dndcompanion.data_class.domain.use_case.ClassUseCases
import com.jlahougue.dndcompanion.data_user_info.domain.use_case.GetCurrentCharacterId

class SpellsModule(
    override val dispatcherProvider: DispatcherProvider,
    override val getCurrentCharacterId: GetCurrentCharacterId,
    override val spellUseCases: SpellUseCases,
    override val classUseCases: ClassUseCases,
    override val characterUseCases: CharacterUseCases
) : ISpellsModule