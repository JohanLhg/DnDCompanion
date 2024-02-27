package com.jlahougue.dndcompanion.feature_authentication.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.authentication_domain.model.InvalidAuthException
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.feature_authentication.di.IAuthenticationModule
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.AuthUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val module: IAuthenticationModule
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AuthUiEvent>(1)
    val event: SharedFlow<AuthUiEvent> = _event.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.update {
                    it.copy(email = event.email)
                }
            }
            is LoginEvent.PasswordChanged -> {
                _state.update {
                    it.copy(password = event.password)
                }
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