package com.jlahougue.feature.settings_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.authentication_presentation.email_change_dialog.EmailChangeDialogEvent
import com.jlahougue.authentication_presentation.email_change_dialog.EmailChangeDialogState
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.feature.settings_domain.SettingsModule
import com.jlahougue.settings_presentation.R
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val module: SettingsModule
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    private val _signOut = MutableSharedFlow<Boolean>()
    val signOut = _signOut.asSharedFlow()

    private val _switchCharacter = MutableSharedFlow<Boolean>()
    val switchCharacter = _switchCharacter.asSharedFlow()

    private val _message = MutableSharedFlow<UiText>()
    val message = _message.asSharedFlow()

    init {
        viewModelScope.launch(module.dispatcherProvider.io) {
            module.userInfoUseCases.getUserInfo().collectLatest { userInfo ->
                _state.update {
                    it.copy(
                        unitSystem = userInfo.unitSystem
                    )
                }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnLanguageChanged -> {
                TODO()
            }
            is SettingsEvent.OnUnitSystemChanged -> {
                module.userInfoUseCases.updateUserInfo(unitSystem = event.unitSystem)
            }
            SettingsEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        emailChangeDialogState = it.emailChangeDialogState.copy(
                            isShown = true
                        )
                    )
                }
            }
            SettingsEvent.OnPasswordChange -> {
                TODO()
            }
            SettingsEvent.OnSignOut -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _signOut.emit(true)
                    module.authUseCases.signOut()
                }
            }
            SettingsEvent.OnCharacterSwitch -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _switchCharacter.emit(true)
                }
                module.userInfoUseCases.updateUserInfo(characterId = -1L)
            }
            is SettingsEvent.OnEmailChangeDialogEvent -> onEmailChangeDialogEvent(event.event)
        }
    }

    private fun onEmailChangeDialogEvent(event: EmailChangeDialogEvent) {
        when (event) {
            EmailChangeDialogEvent.OnDismiss -> {
                _state.update {
                    it.copy(
                        emailChangeDialogState = EmailChangeDialogState()
                    )
                }
            }
            is EmailChangeDialogEvent.OnEmailChange -> {
                _state.update {
                    it.copy(
                        emailChangeDialogState = it.emailChangeDialogState.copy(
                            email = event.email,
                            isEmailValid = true
                        )
                    )
                }
            }
            is EmailChangeDialogEvent.OnPasswordChange -> {
                _state.update {
                    it.copy(
                        emailChangeDialogState = it.emailChangeDialogState.copy(
                            password = event.password,
                            isPasswordValid = true
                        )
                    )
                }
            }
            EmailChangeDialogEvent.OnConfirm -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    state.value.emailChangeDialogState.let { state ->
                        if (state.email.isBlank() && state.password.isBlank()) {
                            _state.update {
                                it.copy(
                                    emailChangeDialogState = state.copy(
                                        isEmailValid = state.email.isNotBlank(),
                                        isPasswordValid = state.password.isNotBlank()
                                    )
                                )
                            }
                            _message.emit(UiText.StringResource(R.string.error_email_password_empty))
                            return@launch
                        }
                        module.authUseCases.changeEmail(
                            state.email,
                            state.password,
                            onError = { message ->
                                viewModelScope.launch(module.dispatcherProvider.main) {
                                    _message.emit(message)
                                }
                            }
                        ) { success ->
                            if (success) {
                                _state.update {
                                    it.copy(
                                        emailChangeDialogState = EmailChangeDialogState()
                                    )
                                }
                            } else {
                                _state.update {
                                    it.copy(
                                        emailChangeDialogState = it.emailChangeDialogState.copy(
                                            isEmailValid = false
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}