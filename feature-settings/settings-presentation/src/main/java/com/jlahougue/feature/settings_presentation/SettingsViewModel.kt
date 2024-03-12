package com.jlahougue.feature.settings_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlahougue.feature.settings_domain.SettingsModule
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
                TODO()
            }
            SettingsEvent.OnPasswordChange -> {
                TODO()
            }
            SettingsEvent.OnSignOut -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _signOut.emit(true)
                }
                module.authUseCases.signOut()
            }
            SettingsEvent.OnCharacterSwitch -> {
                viewModelScope.launch(module.dispatcherProvider.io) {
                    _switchCharacter.emit(true)
                }
                module.userInfoUseCases.updateUserInfo(characterId = -1L)
            }
        }
    }
}