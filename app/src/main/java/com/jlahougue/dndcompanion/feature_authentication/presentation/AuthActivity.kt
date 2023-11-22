package com.jlahougue.dndcompanion.feature_authentication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extensions.viewModelFactory
import com.jlahougue.dndcompanion.feature_authentication.presentation.login.LoginScreen
import com.jlahougue.dndcompanion.feature_authentication.presentation.login.LoginViewModel
import com.jlahougue.dndcompanion.feature_authentication.presentation.register.RegisterScreen
import com.jlahougue.dndcompanion.feature_authentication.presentation.register.RegisterViewModel
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.Screen
import com.jlahougue.dndcompanion.ui.theme.DnDCompanionTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DnDCompanionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(
                            route = Screen.LoginScreen.route
                        ) {
                            val viewModel = viewModel<LoginViewModel>(
                                factory = viewModelFactory {
                                    LoginViewModel(DnDCompanionApp.authModule.authUseCases)
                                }
                            )
                            LoginScreen(
                                state = viewModel.state.value,
                                onEvent = viewModel::onEvent,
                                navigateToRegister = {
                                    navController.navigate(Screen.RegisterScreen.route)
                                },
                                navigateToNext = { },
                                events = viewModel.event
                            )
                        }
                        composable(
                            route = Screen.RegisterScreen.route
                        ) {
                            val viewModel = viewModel<RegisterViewModel>(
                                factory = viewModelFactory {
                                    RegisterViewModel(DnDCompanionApp.authModule.authUseCases)
                                }
                            )
                            RegisterScreen(
                                state = viewModel.state.value,
                                onEvent = viewModel::onEvent,
                                navigateToLogin = {
                                    navController.navigate(Screen.LoginScreen.route)
                                },
                                navigateToNext = { },
                                events = viewModel.event
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    DnDCompanionTheme {
    }
}