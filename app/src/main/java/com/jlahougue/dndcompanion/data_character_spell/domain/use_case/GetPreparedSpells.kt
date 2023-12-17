package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_character_spell.domain.repository.ICharacterSpellRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.StateFlow

class GetPreparedSpells(
    dispatcherProvider: DispatcherProvider,
    userInfoRepository: IUserInfoRepository,
    spellRepository: ICharacterSpellRepository,
    getSpellList: GetSpellList
): GetSpellsBis(dispatcherProvider, userInfoRepository, spellRepository, getSpellList) {

    override suspend operator fun invoke(): StateFlow<List<SpellLevel>> {
        return get(SpellFilter.Prepared)
    }
}