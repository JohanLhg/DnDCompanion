package com.jlahougue.dndcompanion.feature_spells.di

import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

interface ISpellsModule {
    val dispatcherProvider: DispatcherProvider
    val userInfoUseCases: UserInfoUseCases
    val spellUseCases: SpellUseCases
    val classUseCases: ClassUseCases
    val characterUseCases: CharacterUseCases
}