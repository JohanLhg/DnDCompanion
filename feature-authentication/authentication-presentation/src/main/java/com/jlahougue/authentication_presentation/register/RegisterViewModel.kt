package com.jlahougue.authentication_presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.authentication_domain.IAuthenticationModule
import com.jlahougue.authentication_domain.model.InvalidAuthException
import com.jlahougue.authentication_presentation.R
import com.jlahougue.authentication_presentation.util.AuthUiEvent
import com.jlahougue.core_domain.util.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val module: IAuthenticationModule
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<AuthUiEvent>(1)
    val event: SharedFlow<AuthUiEvent> = _event.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> {
                _state.update {
                    it.copy(email = event.email)
                }
            }
            is RegisterEvent.PasswordChanged -> {
                _state.update {
                    it.copy(password = event.password)
                }
            }
            is RegisterEvent.ConfirmPasswordChanged -> {
                _state.update {
                    it.copy(confirmPassword = event.confirmPassword)
                }
            }
            is RegisterEvent.Register -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    try {
                        module.authUseCases.register(
                            state.value.email,
                            state.value.password,
                            state.value.confirmPassword
                        ) { success ->
                            if (success) {
                                viewModelScope.launch {
                                    _event.emit(
                                        AuthUiEvent.ShowSnackbar(
                                            UiText.StringResource(R.string.register_successful)
                                        )
                                    )
                                    _event.emit(AuthUiEvent.NavigateToNextScreen)
                                }
                                return@register
                            }
                            viewModelScope.launch {
                                _event.emit(
                                    AuthUiEvent.ShowSnackbar(
                                        UiText.StringResource(R.string.error_register_failed)
                                    )
                                )
                            }
                        }
                    }
                    catch (e: InvalidAuthException) {
                        _event.emit(AuthUiEvent.ShowSnackbar(e.uiMessage))
                    }
                }
            }
        }
    }
}