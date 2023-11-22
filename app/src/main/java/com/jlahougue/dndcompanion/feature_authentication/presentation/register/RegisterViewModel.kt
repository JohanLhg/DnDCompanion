package com.jlahougue.dndcompanion.feature_authentication.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.UiEvent
import com.jlahougue.dndcompanion.feature_authentication.domain.model.InvalidAuthException
import com.jlahougue.dndcompanion.feature_authentication.domain.use_case.AuthUseCases
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    private val _event = MutableSharedFlow<UiEvent>()
    val event: SharedFlow<UiEvent> = _event.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> {
                _state.value = state.value.copy(email = event.email)
            }
            is RegisterEvent.PasswordChanged -> {
                _state.value = state.value.copy(password = event.password)
            }
            is RegisterEvent.ConfirmPasswordChanged -> {
                _state.value = state.value.copy(confirmPassword = event.confirmPassword)
            }
            is RegisterEvent.Register -> {
                viewModelScope.launch {
                    try {
                        authUseCases.register(
                            state.value.email,
                            state.value.password,
                            state.value.confirmPassword
                        ) { success ->
                            if (success) {
                                viewModelScope.launch {
                                    _event.emit(UiEvent.ShowSnackbarResource(R.string.register_successful))
                                    _event.emit(UiEvent.NavigateToNextScreen)
                                }
                                return@register
                            }
                            viewModelScope.launch {
                                _event.emit(UiEvent.ShowSnackbarResource(R.string.error_register_failed))
                            }
                        }
                    }
                    catch (e: InvalidAuthException) {
                        _event.emit(UiEvent.ShowSnackbarResource(e.messageId))
                    }
                }
            }
        }
    }
}