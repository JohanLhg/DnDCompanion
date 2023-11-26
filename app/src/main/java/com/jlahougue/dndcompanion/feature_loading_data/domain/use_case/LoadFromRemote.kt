package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class LoadFromRemote(
    title: UiText
) {

    var _state = MutableStateFlow(LoadFromRemoteSate(title))
    val state = _state.asStateFlow()

    abstract operator fun invoke()

    fun onApiEvent(event: ApiEvent) {
        when(event) {
            is ApiEvent.Error -> TODO()
            is ApiEvent.SkipCall -> {
                _state.value = _state.value.copy(
                    actionState = LoadFromRemoteSate.ActionState.FINISHED
                )
            }
            is ApiEvent.Skip -> {
                _state.value = _state.value.copy(
                    progress = _state.value.progress + event.count
                )
            }
            is ApiEvent.SetMaxProgress -> {
                _state.value = _state.value.copy(
                    progressMax = event.max
                )
            }
            is ApiEvent.UpdateProgress -> {
                _state.value = _state.value.copy(
                    progress = _state.value.progress + 1
                )
            }
            is ApiEvent.Finish -> {
                _state.value = _state.value.copy(
                    actionState = LoadFromRemoteSate.ActionState.FINISHED
                )
            }
        }
    }

    fun onStateChange(state: LoadFromRemoteSate) {
        _state.value = state
    }
}