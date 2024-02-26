package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.R
import com.jlahougue.spell_domain.repository.ISpellRepository
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