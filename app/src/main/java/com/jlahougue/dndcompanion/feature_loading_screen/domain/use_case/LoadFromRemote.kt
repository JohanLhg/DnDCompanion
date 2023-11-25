package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import android.util.Log
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class LoadFromRemote(
    title: UiText
) {

    private var _state = MutableStateFlow(LoadFromRemoteSate(title))
    val state = _state.asStateFlow()

    fun onApiEvent(event: ApiEvent) {
        when(event) {
            is ApiEvent.Error -> TODO()
            is ApiEvent.SkipCall -> {
                _state.value = _state.value.copy(
                    finished = true
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
                    finished = true
                )
            }
        }
        if (_state.value.progressMax < 100 || _state.value.progress % 100 == 0)
            Log.d("LoadFromRemote", "${this.javaClass.simpleName}: $event\n${_state.value}")
    }

    fun onStateChange(state: LoadFromRemoteSate) {
        _state.value = state
    }
}