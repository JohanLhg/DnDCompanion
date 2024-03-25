package com.jlahougue.feature.authentication_presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_presentation.util.asUiText
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.core_presentation.util.UiText
import com.jlahougue.feature.authentication_domain.IAuthenticationModule
import com.jlahougue.feature.authentication_presentation.R
import com.jlahougue.feature.authentication_presentation.util.AuthUiEvent
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
                    module.authUseCases.register(
                        state.value.email,
                        state.value.password,
                        state.value.confirmPassword,
                        ::onAuthenticationResult
                    )
                }
            }
        }
    }

    private fun onAuthenticationResult(result: Result<String, AuthenticationError>) {
        when (result) {
            is Result.Success -> {
                viewModelScope.launch {
                    _event.emit(
                        AuthUiEvent.ShowMessage(
                            UiText.StringResource(R.string.login_successful)
                        )
                    )
                    _event.emit(AuthUiEvent.NavigateToNextScreen)
                }
            }
            is Result.Failure -> {
                viewModelScope.launch {
                    _event.emit(
                        AuthUiEvent.ShowMessage(
                            result.error.asUiText()
                        )
                    )
                }
            }
        }
    }
}