package com.jlahougue.dndcompanion.activity.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.activity.util.sharedViewModel
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_authentication.presentation.login.LoginScreen
import com.jlahougue.dndcompanion.feature_authentication.presentation.login.LoginViewModel
import com.jlahougue.dndcompanion.feature_authentication.presentation.register.RegisterScreen
import com.jlahougue.dndcompanion.feature_authentication.presentation.register.RegisterViewModel
import com.jlahougue.dndcompanion.feature_loading_data.presentation.LoadingEvent
import com.jlahougue.dndcompanion.feature_loading_data.presentation.LoadingScreen
import com.jlahougue.dndcompanion.feature_loading_data.presentation.LoadingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun NavGraphBuilder.authenticationNavigation(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    route: String,
    isUserAuthenticated: Boolean,
    navigateToNext: () -> Unit = { }
) {
    val startDestination = if (isUserAuthenticated) {
        AuthScreen.LoadingScreen.route
    } else {
        AuthScreen.LoginScreen.route
    }
    navigation(
        route = route,
        startDestination = startDestination
    ) {
        composable(
            route = AuthScreen.LoginScreen.route
        ) { entry ->
            val loadingViewModel = entry.sharedViewModel<LoadingViewModel>(
                navController = navController,
                factory = viewModelFactory {
                    LoadingViewModel(DnDCompanionApp.loadingModule)
                }
            )
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    loadingViewModel.onEvent(LoadingEvent.StartLoading)
                }
            }
            val viewModel = viewModel<LoginViewModel>(
                factory = viewModelFactory {
                    LoginViewModel(DnDCompanionApp.authModule)
                }
            )
            LoginScreen(
                state = viewModel.state.value,
                onEvent = viewModel::onEvent,
                events = viewModel.event,
                navigateToRegister = {
                    navigateTo(navController, AuthScreen.RegisterScreen)
                },
                navigateToNext = {
                    navigateTo(navController, AuthScreen.LoadingScreen)
                }
            )
        }
        composable(
            route = AuthScreen.RegisterScreen.route
        ) { entry ->
            val loadingViewModel = entry.sharedViewModel<LoadingViewModel>(
                navController = navController,
                factory = viewModelFactory {
                    LoadingViewModel(DnDCompanionApp.loadingModule)
                }
            )
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    loadingViewModel.onEvent(LoadingEvent.StartLoading)
                }
            }
            val viewModel = viewModel<RegisterViewModel>(
                factory = viewModelFactory {
                    RegisterViewModel(DnDCompanionApp.authModule)
                }
            )
            RegisterScreen(
                state = viewModel.state.value,
                onEvent = viewModel::onEvent,
                events = viewModel.event,
                navigateToLogin = {
                    navigateTo(navController, AuthScreen.LoginScreen)
                },
                navigateToNext = {
                    navigateTo(navController, AuthScreen.LoadingScreen)
                }
            )
        }
        composable(
            route = AuthScreen.LoadingScreen.route
        ) { entry ->
            val viewModel = entry.sharedViewModel<LoadingViewModel>(
                navController = navController,
                factory = viewModelFactory {
                    LoadingViewModel(DnDCompanionApp.loadingModule)
                }
            )
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    viewModel.onEvent(LoadingEvent.StartLoading)
                }
            }
            LoadingScreen(
                state = viewModel.state.value,
                onEvent = viewModel::onEvent,
                events = viewModel.event,
                navigateToNext = navigateToNext
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object LoginScreen : AuthScreen("login_screen")
    data object RegisterScreen : AuthScreen("register_screen")
    data object LoadingScreen : AuthScreen("loading_screen")
}

private fun navigateTo(
    navController: NavHostController,
    screen: AuthScreen
) {
    when (screen) {
        is AuthScreen.LoginScreen -> {
            navController.navigate(screen.route)
        }
        is AuthScreen.RegisterScreen -> {
            navController.navigate(screen.route)
        }
        is AuthScreen.LoadingScreen -> {
            navController.navigate(screen.route)
        }
    }
}