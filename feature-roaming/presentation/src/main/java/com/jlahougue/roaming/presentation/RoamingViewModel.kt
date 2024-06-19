package com.jlahougue.roaming.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class RoamingViewModel: ViewModel() {

    var state by mutableStateOf(RoamingState())
        private set

    private val eventChannel = Channel<RoamingEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: RoamingAction) {
        when (action) {
            else -> {}
        }
    }
}