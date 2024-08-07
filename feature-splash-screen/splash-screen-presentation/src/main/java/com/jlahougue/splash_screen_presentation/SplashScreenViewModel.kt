package com.jlahougue.splash_screen_presentation

import androidx.lifecycle.ViewModel
import com.jlahougue.authentication_domain.use_case.IsLoggedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SplashScreenViewModel(
    private val isLoggedIn: IsLoggedIn
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    fun isUserAuthenticated(): Boolean {
        val isAuthenticated = isLoggedIn()
        _isLoading.update { false }
        return isAuthenticated
    }
}