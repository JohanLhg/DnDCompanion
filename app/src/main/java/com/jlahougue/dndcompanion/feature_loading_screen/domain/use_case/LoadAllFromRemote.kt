package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch

class LoadAllFromRemote(
    private val dispatcherProvider: DispatcherProvider,
    loadSpellsFromRemote: LoadSpellsFromRemote,
    loadDamageTypesFromRemote: LoadDamageTypesFromRemote
) : LoadFromRemote(UiText.StringResource(R.string.loading)) {

    private val _waitingFor = MutableStateFlow(
        listOf(
            DAMAGE_TYPES_KEY,
            SPELLS_KEY
        )
    )
    private val waitingFor = _waitingFor.asStateFlow()

    private val useCaseMap = mapOf(
        DAMAGE_TYPES_KEY to loadDamageTypesFromRemote,
        SPELLS_KEY to loadSpellsFromRemote
    )

    private val useCasePrerequisites = mapOf(
        SPELLS_KEY to listOf(DAMAGE_TYPES_KEY)
    )

    init {
        useCaseMap.keys.forEach {
            initUseCase(it)
        }
    }

    override operator fun invoke() {
        CoroutineScope(dispatcherProvider.io).launch {
            waitingFor.collect {
                if (it.isEmpty()) {
                    onApiEvent(ApiEvent.Finish)
                    return@collect
                }
                for (identifier in it) {
                    val prerequisites = useCasePrerequisites[identifier]
                    val useCase = useCaseMap[identifier] ?: continue
                    if (!useCase.state.value.hasStarted() &&
                        (prerequisites == null || prerequisites.all {
                                prerequisite -> !it.contains(prerequisite)
                        })) {
                        useCaseMap[identifier]?.invoke()
                    }
                }
            }
        }
    }

    private fun initUseCase(identifier: Int) {
        val loadFromRemote = useCaseMap[identifier]
        if (loadFromRemote == null) {
            val errorMessage = UiText.DynamicString("No use case found for identifier $identifier")
            onApiEvent(ApiEvent.Error(errorMessage))
            return
        }
        val job = CoroutineScope(dispatcherProvider.io).launch {
            loadFromRemote.state.takeWhile {
                !it.hasFinished()
            }.collect { state ->
                if (state.hasFinished()) {
                    finished(identifier)
                    return@collect
                }
                if (waitingFor.value.firstOrNull() == identifier) {
                    onStateChange(state)
                }
            }
        }
        job.invokeOnCompletion {
            finished(identifier)
        }
    }

    private fun finished(identifier: Int) {
        _waitingFor.value = _waitingFor.value.filter { it != identifier }
    }

    companion object {
        const val DAMAGE_TYPES_KEY = 0
        const val SPELLS_KEY = 1
    }
}