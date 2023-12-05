package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import android.util.Log
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _isUserAuthenticated = MutableStateFlow(false)
    private val isUserAuthenticated = _isUserAuthenticated.asStateFlow()

    private val useCaseMap = mapOf(
        CLASSES_KEY to loadClasses,
        DAMAGE_TYPES_KEY to loadDamageTypes,
        SPELLS_KEY to loadSpells,
        PROPERTIES_KEY to loadProperties,
        WEAPONS_KEY to loadWeapons,
        CHARACTERS_KEY to loadCharacters
    )

    private val useCasePrerequisites = mapOf(
        SPELLS_KEY to { hasFinished(DAMAGE_TYPES_KEY) },
        CHARACTERS_KEY to { isUserAuthenticated.value }
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
            waitingFor.collectLatest {
                if (it.isEmpty()) {
                    onApiEvent(ApiEvent.Finish)
                    return@collectLatest
                }
                it.forEach { identifier ->
                    startIfPossible(identifier)
                }
            }
        }
        CoroutineScope(dispatcherProvider.io).launch {
            isUserAuthenticated.collectLatest {
                Log.d("LoadAll", "User is authenticated : $it")
                val identifiers = waitingFor.value
                Log.d("LoadAll", "Waiting for : $identifiers")
                identifiers.forEach { identifier ->
                    startIfPossible(identifier)
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

    private fun startIfPossible(identifier: Int) {
        val prerequisites = useCasePrerequisites[identifier] ?: { true }
        val useCase = useCaseMap[identifier] ?: return
        if (!useCase.state.value.hasStarted() && prerequisites.invoke()) {
            useCase.invoke()
        }
    }

    private fun hasFinished(identifier: Int) = useCaseMap[identifier]?.state?.value?.hasFinished() ?: false

    fun onUserAuthenticated() {
        _isUserAuthenticated.value = true
    }

    companion object {
        const val CLASSES_KEY = 0
        const val DAMAGE_TYPES_KEY = 1
        const val SPELLS_KEY = 2
        const val PROPERTIES_KEY = 3
        const val WEAPONS_KEY = 4
        const val CHARACTERS_KEY = 5
    }
}