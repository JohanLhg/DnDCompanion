package com.jlahougue.dndcompanion.feature_splash_screen.presentation

import androidx.lifecycle.ViewModel
import com.jlahougue.dndcompanion.data_authentication.domain.use_case.IsLoggedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashScreenViewModel(
    private val isLoggedIn: IsLoggedIn
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun isUserAuthenticated(): Boolean {
        val isAuthenticated = isLoggedIn()
        _isLoading.value = false
        return isAuthenticated
    }
}