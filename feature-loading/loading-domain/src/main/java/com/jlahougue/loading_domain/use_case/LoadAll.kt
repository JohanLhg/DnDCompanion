package com.jlahougue.loading_domain.use_case

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.loading_domain.util.LoaderKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoadAll(
    private val dispatcherProvider: DispatcherProvider,
    loadCharacters: LoadCharacters,
    loadClasses: LoadClasses,
    loadSpells: LoadSpells,
    loadDamageTypes: LoadDamageTypes,
    loadProperties: LoadProperties,
    loadWeapons: LoadWeapons
) : LoadFromRemote(LoaderKey.ALL) {

    private val _isUserAuthenticated = MutableStateFlow(false)
    private val isUserAuthenticated = _isUserAuthenticated.asStateFlow()

    private val useCaseMap = mapOf(
        LoaderKey.CLASSES to loadClasses,
        LoaderKey.DAMAGE_TYPES to loadDamageTypes,
        LoaderKey.SPELLS to loadSpells,
        LoaderKey.PROPERTIES to loadProperties,
        LoaderKey.WEAPONS to loadWeapons,
        LoaderKey.CHARACTERS to loadCharacters
    )

    private val useCasePrerequisites = mapOf(
        LoaderKey.SPELLS to { hasFinished(LoaderKey.DAMAGE_TYPES) },
        LoaderKey.CHARACTERS to { isUserAuthenticated.value }
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
            waitingFor.combine(isUserAuthenticated) { waitingFor, _ ->
                if (waitingFor.isEmpty()) {
                    onApiEvent(ApiEvent.Finish)
                    return@combine
                }
                waitingFor.forEach { identifier ->
                    startIfPossible(identifier)
                }
            }.collectLatest { }
        }
    }

    private fun initUseCase(identifier: LoaderKey) {
        val loadFromRemote = useCaseMap[identifier] ?: return
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

    private fun finished(identifier: LoaderKey) {
        _waitingFor.update { waitingFor.value.filter { it != identifier } }
    }

    private fun startIfPossible(identifier: LoaderKey) {
        val prerequisites = useCasePrerequisites[identifier] ?: { true }
        val useCase = useCaseMap[identifier] ?: return
        if (!useCase.state.value.hasStarted() && prerequisites.invoke()) {
            useCase.invoke()
        }
    }

    private fun hasFinished(identifier: LoaderKey) = useCaseMap[identifier]?.state?.value?.hasFinished() ?: false

    fun onUserAuthenticated() {
        _isUserAuthenticated.update { true }
    }
}