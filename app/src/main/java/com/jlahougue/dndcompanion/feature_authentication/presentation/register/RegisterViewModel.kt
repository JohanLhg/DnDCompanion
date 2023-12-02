package com.jlahougue.dndcompanion.feature_authentication.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.feature_authentication.di.IAuthModule
import com.jlahougue.dndcompanion.feature_authentication.domain.model.InvalidAuthException
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.AuthUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val module: IAuthModule
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    private val _event = MutableSharedFlow<AuthUiEvent>(1)
    val event: SharedFlow<AuthUiEvent> = _event.asSharedFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.CheckIfLoggedIn -> {
                if (module.authUseCases.isLoggedIn()) {
                    viewModelScope.launch {
                        _event.emit(AuthUiEvent.NavigateToNextScreen)
                    }
                }
            }
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