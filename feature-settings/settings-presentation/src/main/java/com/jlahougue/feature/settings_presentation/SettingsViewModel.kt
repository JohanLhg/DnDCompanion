package com.jlahougue.feature.settings_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.authentication_presentation.email_change_dialog.EmailChangeDialogEvent
import com.jlahougue.authentication_presentation.email_change_dialog.EmailChangeDialogState
import com.jlahougue.authentication_presentation.util.asUiText
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.core_presentation.util.UiEvent
import com.jlahougue.core_presentation.util.UiText
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

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    private val _signOut = MutableSharedFlow<Boolean>()
    val signOut = _signOut.asSharedFlow()

    private val _switchCharacter = MutableSharedFlow<Boolean>()
    val switchCharacter = _switchCharacter.asSharedFlow()

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
                    module.authUseCases.changeEmail(
                        state.value.emailChangeDialogState.email,
                        state.value.emailChangeDialogState.password,
                        ::onEmailChangeResult
                    )
                }
            }
        }
    }

    private fun onEmailChangeResult(result: Result<String, EmailChangeError>) {
        viewModelScope.launch {
            when (result) {
                is Result.Success -> {
                    _uiEvent.emit(
                        UiEvent.ShowError(UiText.StringResource(R.string.email_changed_successfully))
                    )
                    _state.update {
                        it.copy(
                            emailChangeDialogState = EmailChangeDialogState()
                        )
                    }
                }
                is Result.Failure -> {
                    when (result.error) {
                        EmailChangeError.USER_NOT_FOUND -> {
                            _signOut.emit(true)
                        }
                        EmailChangeError.PASSWORD_EMPTY,
                        EmailChangeError.INVALID_CREDENTIAL -> {
                            _state.update {
                                it.copy(
                                    emailChangeDialogState = it.emailChangeDialogState.copy(
                                        isPasswordValid = false
                                    )
                                )
                            }
                        }
                        EmailChangeError.EMAIL_ALREADY_IN_USE,
                        EmailChangeError.EMAIL_EMPTY,
                        EmailChangeError.EMAIL_INVALID -> {
                            _state.update {
                                it.copy(
                                    emailChangeDialogState = it.emailChangeDialogState.copy(
                                        isEmailValid = false
                                    )
                                )
                            }
                        }
                        else -> { }
                    }
                    _uiEvent.emit(
                        UiEvent.ShowError(result.error.asUiText())
                    )
                }
            }
        }
    }
}