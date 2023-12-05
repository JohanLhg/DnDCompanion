package com.jlahougue.dndcompanion.feature_authentication.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.data_authentication.domain.model.InvalidAuthException
import com.jlahougue.dndcompanion.feature_authentication.di.IAuthenticationModule
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.AuthUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val module: IAuthenticationModule
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private val _event = MutableSharedFlow<AuthUiEvent>(1)
    val event: SharedFlow<AuthUiEvent> = _event.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.value = state.value.copy(email = event.email)
            }
            is LoginEvent.PasswordChanged -> {
                _state.value = state.value.copy(password = event.password)
            }
            is LoginEvent.Login -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    try {
                        module.authUseCases.login(
                            state.value.email,
                            state.value.password
                        ) { success ->
                            if (success) {
                                viewModelScope.launch {
                                    _event.emit(
                                        AuthUiEvent.ShowSnackbar(
                                            UiText.StringResource(R.string.login_successful)
                                        )
                                    )
                                    _event.emit(AuthUiEvent.NavigateToNextScreen)
                                }
                                return@login
                            }
                            viewModelScope.launch {
                                _event.emit(
                                    AuthUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.error_login_failed)
                                    )
                                )
                            }
                        }
                    } catch (e: InvalidAuthException) {
                        _event.emit(
                            AuthUiEvent.ShowSnackbar(e.uiMessage)
                        )
                    }
                }
            }
        }
    }
}