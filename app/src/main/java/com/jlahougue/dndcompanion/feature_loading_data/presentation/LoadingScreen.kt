package com.jlahougue.dndcompanion.feature_loading_data.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.feature_loading_data.presentation.util.LoadingUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@Composable
fun LoadingScreen(
    state: LoadingState,
    events: SharedFlow<LoadingUiEvent>,
    navigateToNext: () -> Unit
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LaunchedEffect(Unit) {
            events.collect { event ->
                when (event) {
                    is LoadingUiEvent.NavigateToNextScreen -> {
                        navigateToNext()
                    }
                    is LoadingUiEvent.ShowSnackbar -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(message = event.message.getString(context))
                        }
                    }
                }
            }
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = state.title.getString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.medium)
                )
                LinearProgressIndicator(
                    progress = { state.progress },
                )
            }
        }
    }
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun LoadingPreview() {
    DnDCompanionTheme {
        LoadingScreen(
            state = LoadingState(
                title = UiText.StringResource(R.string.loading),
                progress = 0.5f
            ),
            events = MutableSharedFlow<LoadingUiEvent>().asSharedFlow(),
            navigateToNext = {}
        )
    }
}