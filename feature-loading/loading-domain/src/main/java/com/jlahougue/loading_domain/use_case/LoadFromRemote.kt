package com.jlahougue.loading_domain.use_case

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.loading_domain.util.LoaderKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class LoadFromRemote(identifier: LoaderKey) {

    private var _state = MutableStateFlow(LoadSate(identifier))
    val state = _state.asStateFlow()

    open operator fun invoke() {
        onApiEvent(ApiEvent.Start)
    }

    fun onApiEvent(event: ApiEvent) {
        when(event) {
            is ApiEvent.Start -> {
                _state.update {
                    _state.value.copy(
                        actionState = LoadSate.ActionState.STARTED
                    )
                }
            }
            is ApiEvent.Error -> {
                onApiEvent(ApiEvent.Finish)
            }
            is ApiEvent.Skip -> {
                _state.update {
                    _state.value.copy(
                        progress = _state.value.progress + event.count
                    )
                }
            }
            is ApiEvent.SetMaxProgress -> {
                _state.update {
                    _state.value.copy(
                        progressMax = event.max
                    )
                }
            }
            is ApiEvent.UpdateProgress -> {
                _state.update {
                    _state.value.copy(
                        progress = _state.value.progress + 1
                    )
                }
            }
            is ApiEvent.Finish -> {
                _state.update {
                    _state.value.copy(
                        actionState = LoadSate.ActionState.FINISHED
                    )
                }
            }
        }
    }

    fun onStateChange(state: LoadSate) {
        _state.update { state }
    }
}