package com.jlahougue.loading_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import com.jlahougue.loading_domain.util.LoaderKey
import com.jlahougue.spell_domain.repository.ISpellRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadSpells(
    private val dispatcherProvider: DispatcherProvider,
    private val spellRepository: ISpellRepository,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(LoaderKey.SPELLS) {
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