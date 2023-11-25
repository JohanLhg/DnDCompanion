package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.core.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadSpellsFromRemote(
    private val dispatcherProvider: DispatcherProvider,
    private val spellRepository: ISpellRepository,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_spells)) {
    override operator fun invoke() {
        CoroutineScope(dispatcherProvider.io).launch {
            spellRepository.loadAll(
                damageTypeRepository.getNames(),
                ::onApiEvent
            )

            onApiEvent(ApiEvent.Finish)
        }
    }
}