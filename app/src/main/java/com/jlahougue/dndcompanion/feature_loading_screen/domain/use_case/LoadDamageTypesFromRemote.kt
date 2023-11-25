package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.repository.IDamageTypeRepository
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoadDamageTypesFromRemote(
    private val dispatcherProvider: DispatcherProvider,
    private val damageTypeRepository: IDamageTypeRepository
): LoadFromRemote(UiText.StringResource(R.string.loading_damage_types)) {

    suspend operator fun invoke(): StateFlow<LoadFromRemoteSate> {
        CoroutineScope(dispatcherProvider.io).launch {
            damageTypeRepository.loadAll(
                ::onApiEvent
            )

            onApiEvent(ApiEvent.Finish)
        }
        return state
    }
}