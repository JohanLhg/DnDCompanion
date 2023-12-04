package com.jlahougue.dndcompanion.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jlahougue.dndcompanion.activity.navigation.authenticationNavigation
import com.jlahougue.dndcompanion.activity.navigation.characterSelectionNavigation
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.feature_splash_screen.presentation.SplashScreenViewModel

class DnDCompanionActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreenViewModel by viewModels<SplashScreenViewModel> {
            viewModelFactory {
                SplashScreenViewModel()
            }
        }
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreenViewModel.isLoading.value
            }
        }

        setContent {
            DnDCompanionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val scope = rememberCoroutineScope()
                    navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenGroup.Authentication.route
                    ) {
                        authenticationNavigation(
                            navController = navController,
                            route = ScreenGroup.Authentication.route,
                            coroutineScope = scope,
                            isUserAuthenticated = splashScreenViewModel.isUserAuthenticated(),
                            navigateToNext = {
                                onNavigationToGroup(ScreenGroup.CharacterSelection)
                            }
                        )
                        characterSelectionNavigation(
                            navController = navController,
                            route = ScreenGroup.CharacterSelection.route,
                            coroutineScope = scope,
                            navigateToNext = {
                            }
                        )
                    }
                }
            }
        }
    }

    fun onNavigationToGroup(screenGroup: ScreenGroup) {
        when (screenGroup) {
            ScreenGroup.Authentication -> {
                navController.navigate(ScreenGroup.Authentication.route)
            }
            ScreenGroup.CharacterSelection -> {
                navController.navigate(ScreenGroup.CharacterSelection.route) {
                    popUpTo(ScreenGroup.Authentication.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}