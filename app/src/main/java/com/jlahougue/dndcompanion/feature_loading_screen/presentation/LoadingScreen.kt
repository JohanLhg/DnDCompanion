package com.jlahougue.dndcompanion.feature_loading_screen.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.ui.spacing
import com.jlahougue.dndcompanion.ui.theme.DnDCompanionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingScreen(
    state: LoadingState,
) {
    Scaffold { paddingValues ->
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
                    text = stringResource(id = state.currentStep),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.medium)
                )
                LinearProgressIndicator(
                    progress = state.progress
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun LoadingPreview() {
    DnDCompanionTheme {
        LoadingScreen(
            state = LoadingState(
                currentStep = R.string.loading
            )
        )
    }
}