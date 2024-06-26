package com.jlahougue.feature.authentication_presentation.login

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.feature.authentication_presentation.R
import com.jlahougue.feature.authentication_presentation.util.AuthUiEvent
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    events: SharedFlow<AuthUiEvent>,
    navigateToRegister: () -> Unit,
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
                    is AuthUiEvent.ShowMessage -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(event.message.getString(context))
                        }
                    }
                    is AuthUiEvent.NavigateToNextScreen -> {
                        navigateToNext()
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
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .width(IntrinsicSize.Min)
            ) {
                Image(
                    painter = painterResource(CoreR.drawable.die),
                    contentDescription = stringResource(CoreR.string.label_logo),
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = MaterialTheme.spacing.medium)
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.label_email)) },
                    value = state.email,
                    onValueChange = { onEvent(LoginEvent.EmailChanged(it)) },
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                )
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.label_password)) },
                    value = state.password,
                    onValueChange = { onEvent(LoginEvent.PasswordChanged(it)) },
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.small),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onEvent(LoginEvent.Login) }
                    )
                )
                Button(
                    onClick = { onEvent(LoginEvent.Login) },
                    shape = OutlinedTextFieldDefaults.shape,
                    modifier = Modifier
                        .padding(top = MaterialTheme.spacing.medium)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.login).uppercase(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.text_register_now)),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    ),
                    overflow = TextOverflow.Visible,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.medium)
                ) {
                    navigateToRegister()
                }
            }
        }
    }
}