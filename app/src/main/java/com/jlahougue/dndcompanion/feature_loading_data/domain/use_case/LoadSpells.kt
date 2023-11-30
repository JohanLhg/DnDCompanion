package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_damage_type.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadSpells(
    private val dispatcherProvider: DispatcherProvider,
    private val spellRepository: ISpellRepository,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_spells)) {
    override operator fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            spellRepository.loadAll(
                damageTypeRepository.getNames(),
                ::onApiEvent
            )
        }
    }
}