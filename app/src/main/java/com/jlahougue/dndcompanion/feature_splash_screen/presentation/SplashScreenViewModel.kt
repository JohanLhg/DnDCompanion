package com.jlahougue.dndcompanion.feature_splash_screen.presentation

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashScreenViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    fun isUserAuthenticated(): Boolean {
        val isAuthenticated = FirebaseAuth.getInstance().currentUser != null
        _isLoading.value = false
        return isAuthenticated
    }
}