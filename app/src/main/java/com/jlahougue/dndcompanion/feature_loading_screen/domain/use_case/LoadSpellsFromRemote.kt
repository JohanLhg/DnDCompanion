package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import android.util.Log
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.core.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoadSpellsFromRemote(
    private val dispatcherProvider: DispatcherProvider,
    private val spellRepository: ISpellRepository,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_spells)) {
    suspend operator fun invoke(): StateFlow<LoadFromRemoteSate> {
        CoroutineScope(dispatcherProvider.io).launch {
            Log.d("LoadSpellsFromRemote", "LoadSpellsFromRemote: ${damageTypeRepository.getNames()}")
            spellRepository.loadAll(
                damageTypeRepository.getNames(),
                ::onApiEvent
            )

            onApiEvent(ApiEvent.Finish)
        }
        return state
    }
}