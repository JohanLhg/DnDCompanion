package com.jlahougue.dndcompanion.feature_authentication.presentation.login

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

class LoginViewModel(
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private val _event = MutableSharedFlow<UiEvent>()
    val event: SharedFlow<UiEvent> = _event.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.value = state.value.copy(email = event.email)
            }
            is LoginEvent.PasswordChanged -> {
                _state.value = state.value.copy(password = event.password)
            }
            is LoginEvent.Login -> {
                viewModelScope.launch {
                    try {
                        authUseCases.login(
                            state.value.email,
                            state.value.password
                        ) { success ->
                            if (success) {
                                viewModelScope.launch {
                                    _event.emit(UiEvent.NavigateToNextScreen)
                                }
                                return@login
                            }
                            viewModelScope.launch {
                                _event.emit(UiEvent.ShowSnackbarResource(R.string.error_login_failed))
                            }
                        }
                    } catch (e: InvalidAuthException) {
                        _event.emit(UiEvent.ShowSnackbarResource(e.messageId))
                    }
                }
            }
            is LoginEvent.NavigateToRegisterScreen -> {
                viewModelScope.launch {
                    _event.emit(UiEvent.NavigateToRegisterScreen)
                }
            }
        }
    }

}