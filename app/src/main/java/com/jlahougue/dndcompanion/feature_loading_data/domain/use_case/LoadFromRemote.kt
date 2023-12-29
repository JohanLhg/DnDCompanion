package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import android.util.Log
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class LoadFromRemote(title: UiText) {

    private var _state = MutableStateFlow(LoadSate(title))
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
                Log.e("LoadFromRemote", event.message.toString())
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