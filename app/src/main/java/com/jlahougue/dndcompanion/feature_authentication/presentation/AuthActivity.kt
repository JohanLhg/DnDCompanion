package com.jlahougue.dndcompanion.feature_authentication.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.feature_authentication.presentation.login.LoginScreen
import com.jlahougue.dndcompanion.feature_authentication.presentation.login.LoginViewModel
import com.jlahougue.dndcompanion.feature_authentication.presentation.register.RegisterScreen
import com.jlahougue.dndcompanion.feature_authentication.presentation.register.RegisterViewModel
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.Screen
import com.jlahougue.dndcompanion.feature_loading_data.LoadingActivity
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
                    val context = LocalContext.current
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
                                    LoginViewModel(
                                        DnDCompanionApp.appModule,
                                        DnDCompanionApp.authModule.authUseCases
                                    )
                                }
                            )
                            LoginScreen(
                                state = viewModel.state.value,
                                onEvent = viewModel::onEvent,
                                events = viewModel.event,
                                navigateToRegister = {
                                    navController.navigate(Screen.RegisterScreen.route)
                                },
                                navigateToNext = {
                                    val intent = Intent(context, LoadingActivity::class.java)
                                    context.startActivity(intent)
                                }
                            )
                        }
                        composable(
                            route = Screen.RegisterScreen.route
                        ) {
                            val viewModel = viewModel<RegisterViewModel>(
                                factory = viewModelFactory {
                                    RegisterViewModel(
                                        DnDCompanionApp.appModule,
                                        DnDCompanionApp.authModule.authUseCases
                                    )
                                }
                            )
                            RegisterScreen(
                                state = viewModel.state.value,
                                onEvent = viewModel::onEvent,
                                events = viewModel.event,
                                navigateToLogin = {
                                    navController.navigate(Screen.LoginScreen.route)
                                },
                                navigateToNext = {
                                    val intent = Intent(context, LoadingActivity::class.java)
                                    context.startActivity(intent)
                                }
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