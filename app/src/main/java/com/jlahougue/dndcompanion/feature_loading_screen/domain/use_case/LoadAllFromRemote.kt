package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadAllFromRemote(
    private val dispatcherProvider: DispatcherProvider,
    private val loadSpellsFromRemote: LoadSpellsFromRemote,
    private val loadDamageTypesFromRemote: LoadDamageTypesFromRemote
) : LoadFromRemote(UiText.StringResource(R.string.loading)) {

    private var waitingFor = listOf(
        DAMAGE_TYPES_KEY,
        SPELLS_KEY
    )

    operator fun invoke() {
        CoroutineScope(dispatcherProvider.io).launch {
            loadDamageTypesFromRemote().collect { damageTypeState ->
                onStateChange(
                    DAMAGE_TYPES_KEY,
                    damageTypeState,
                    onFinished = {
                        CoroutineScope(dispatcherProvider.io).launch {
                            loadSpellsFromRemote().collect { spellState ->
                                onStateChange(SPELLS_KEY, spellState)
                            }
                        }
                    }
                )
            }
        }
    }

    private fun onStateChange(
        identifier: Int,
        state: LoadFromRemoteSate,
        onFinished: () -> Unit = {}
    ) {
        if (waitingFor.firstOrNull() == identifier) {
            super.onStateChange(state.copy(finished = false))
        }
        if (state.finished) {
            finished(identifier)
            onFinished()
        }
    }

    private fun finished(identifier: Int) {
        waitingFor = waitingFor.minus(identifier)
        if (waitingFor.isEmpty()) {
            onApiEvent(ApiEvent.Finish)
        }
    }

    companion object {
        const val DAMAGE_TYPES_KEY = 0
        const val SPELLS_KEY = 1
    }
}