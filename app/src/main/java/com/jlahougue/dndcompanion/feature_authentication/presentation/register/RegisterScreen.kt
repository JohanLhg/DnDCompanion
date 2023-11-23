package com.jlahougue.dndcompanion.feature_authentication.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.Screen
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.UiEvent
import com.jlahougue.dndcompanion.ui.spacing
import com.jlahougue.dndcompanion.ui.theme.DnDCompanionTheme
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    navigateToLogin: () -> Unit,
    navigateToNext: () -> Unit,
    events: SharedFlow<UiEvent>
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { paddingValues ->

            LaunchedEffect(Unit) {
                events.collect { event ->
                    when (event) {
                        is UiEvent.ShowSnackbar -> {
                            scope.launch {
                                snackbarHostState.showSnackbar(event.message)
                            }
                        }
                        is UiEvent.ShowSnackbarResource -> {
                            scope.launch {
                                snackbarHostState.showSnackbar(context.getString(event.messageId))
                            }
                        }
                        is UiEvent.NavigateToNextScreen -> {
                            navigateToNext()
                        }
                    }
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(IntrinsicSize.Max)
                        .width(IntrinsicSize.Min)
                ) {
                    Image(
                        painter = painterResource(R.drawable.die),
                        contentDescription = stringResource(R.string.label_logo),
                        modifier = Modifier
                            .size(200.dp)
                            .padding(bottom = MaterialTheme.spacing.medium),
                    )
                    OutlinedTextField(
                        label = { Text(text = stringResource(R.string.label_email)) },
                        value = state.email,
                        onValueChange = { onEvent(RegisterEvent.EmailChanged(it)) },
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.small),
                        textStyle = MaterialTheme.typography.bodyLarge,
                    )
                    OutlinedTextField(
                        label = { Text(text = stringResource(R.string.label_password)) },
                        value = state.password,
                        onValueChange = { onEvent(RegisterEvent.PasswordChanged(it)) },
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.small),
                        textStyle = MaterialTheme.typography.bodyLarge,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    OutlinedTextField(
                        label = { Text(text = stringResource(R.string.label_confirm_password)) },
                        value = state.confirmPassword,
                        onValueChange = { onEvent(RegisterEvent.ConfirmPasswordChanged(it)) },
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.small),
                        textStyle = MaterialTheme.typography.bodyLarge,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Button(
                        onClick = { onEvent(RegisterEvent.Register) },
                        shape = TextFieldDefaults.outlinedShape,
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.medium)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.signup).uppercase(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    ClickableText(
                        text = AnnotatedString(stringResource(R.string.text_login_now)),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        ),
                        overflow = TextOverflow.Visible,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = MaterialTheme.spacing.medium)
                    ) {
                        navigateToLogin()
                    }
                }
            }
        }
    )
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun RegisterScreenPreview() {
    DnDCompanionTheme {
        //RegisterScreen()
    }
}