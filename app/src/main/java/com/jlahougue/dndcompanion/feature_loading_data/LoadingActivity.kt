package com.jlahougue.dndcompanion.feature_loading_data

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.domain.util.extension.viewModelFactory
import com.jlahougue.dndcompanion.core.presentation.theme.DnDCompanionTheme
import com.jlahougue.dndcompanion.feature_character_selection.presentation.CharacterSelectionActivity
import com.jlahougue.dndcompanion.feature_loading_data.presentation.LoadingScreen
import com.jlahougue.dndcompanion.feature_loading_data.presentation.LoadingViewModel

class LoadingActivity : ComponentActivity() {
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
                    val viewModel = viewModel<LoadingViewModel>(
                        factory = viewModelFactory {
                            LoadingViewModel(
                                DnDCompanionApp.loadingModule.dispatcherProvider,
                                DnDCompanionApp.loadingModule.loadAll
                            )
                        }
                    )
                    LoadingScreen(
                        state = viewModel.state.value,
                        onEvent = viewModel::onEvent,
                        events = viewModel.event,
                        navigateToNext = {
                            val intent = Intent(context, CharacterSelectionActivity::class.java)
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}