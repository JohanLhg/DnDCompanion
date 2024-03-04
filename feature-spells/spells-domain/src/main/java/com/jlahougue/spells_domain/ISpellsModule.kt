package com.jlahougue.spells_domain

import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.character_spell_domain.use_case.SpellUseCases
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

interface ISpellsModule {
    val dispatcherProvider: DispatcherProvider
    val userInfoUseCases: UserInfoUseCases
    val spellUseCases: SpellUseCases
    val classUseCases: ClassUseCases
    val characterUseCases: CharacterUseCases
}