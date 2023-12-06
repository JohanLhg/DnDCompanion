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

fun NavGraphBuilder.authenticationSection(
    navController: NavHostController,
    coroutineScope: CoroutineScope,
    route: String,
    isUserAuthenticated: Boolean,
    navigateToNext: () -> Unit = { }
) {
    val startDestination = if (isUserAuthenticated) {
        AuthScreen.Loading.route
    } else {
        AuthScreen.Login.route
    }
    navigation(
        route = route,
        startDestination = startDestination
    ) {
        composable(
            route = AuthScreen.Login.route
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
                    LoginViewModel(DnDCompanionApp.authenticationModule)
                }
            )
            LoginScreen(
                state = viewModel.state.value,
                onEvent = viewModel::onEvent,
                events = viewModel.event,
                navigateToRegister = {
                    navigateTo(navController, AuthScreen.Register)
                },
                navigateToNext = {
                    navigateTo(navController, AuthScreen.Loading)
                }
            )
        }
        composable(
            route = AuthScreen.Register.route
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
                    RegisterViewModel(DnDCompanionApp.authenticationModule)
                }
            )
            RegisterScreen(
                state = viewModel.state.value,
                onEvent = viewModel::onEvent,
                events = viewModel.event,
                navigateToLogin = {
                    navigateTo(navController, AuthScreen.Login)
                },
                navigateToNext = {
                    navigateTo(navController, AuthScreen.Loading)
                }
            )
        }
        composable(
            route = AuthScreen.Loading.route
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
                    viewModel.onEvent(LoadingEvent.UserAuthenticated)
                }
            }
            LoadingScreen(
                state = viewModel.state.value,
                events = viewModel.event,
                navigateToNext = navigateToNext
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen("login")
    data object Register : AuthScreen("register")
    data object Loading : AuthScreen("loading")
}

private fun navigateTo(
    navController: NavHostController,
    screen: AuthScreen
) {
    when (screen) {
        is AuthScreen.Login -> {
            navController.navigate(screen.route)
        }
        is AuthScreen.Register -> {
            navController.navigate(screen.route)
        }
        is AuthScreen.Loading -> {
            navController.navigate(screen.route)
        }
    }
}