package com.jlahougue.spells_domain

import com.jlahougue.character_domain.use_case.CharacterUseCases
import com.jlahougue.character_spell_domain.use_case.SpellUseCases
import com.jlahougue.class_domain.use_case.ClassUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.spell_domain.repository.ISpellRepository
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases

class SpellsModule(
    override val dispatcherProvider: DispatcherProvider,
    override val spellRepository: ISpellRepository,
    override val userInfoUseCases: UserInfoUseCases,
    override val spellUseCases: SpellUseCases,
    override val classUseCases: ClassUseCases,
    override val characterUseCases: CharacterUseCases
) : ISpellsModule