package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_sheet.domain.use_case.LoadCharacters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.launch

class LoadAll(
    private val dispatcherProvider: DispatcherProvider,
    loadCharacters: LoadCharacters,
    loadClasses: LoadClasses,
    loadSpells: LoadSpells,
    loadDamageTypes: LoadDamageTypes,
    loadProperties: LoadProperties,
    loadWeapons: LoadWeapons
) : LoadFromRemote(UiText.StringResource(R.string.loading)) {

    private val useCaseMap = mapOf(
        CHARACTERS_KEY to loadCharacters,
        CLASSES_KEY to loadClasses,
        DAMAGE_TYPES_KEY to loadDamageTypes,
        SPELLS_KEY to loadSpells,
        PROPERTIES_KEY to loadProperties,
        WEAPONS_KEY to loadWeapons
    )

    private val useCasePrerequisites = mapOf(
        SPELLS_KEY to listOf(DAMAGE_TYPES_KEY)
    )

    private val _waitingFor = MutableStateFlow(
        useCaseMap.keys.toList()
    )
    private val waitingFor = _waitingFor.asStateFlow()

    init {
        useCaseMap.keys.forEach {
            initUseCase(it)
        }
    }

    override operator fun invoke() {
        super.invoke()
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
        const val CHARACTERS_KEY = 0
        const val CLASSES_KEY = 1
        const val DAMAGE_TYPES_KEY = 2
        const val SPELLS_KEY = 3
        const val PROPERTIES_KEY = 4
        const val WEAPONS_KEY = 5
    }
}