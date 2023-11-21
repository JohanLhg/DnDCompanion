package com.jlahougue.dndcompanion.feature_authentication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.feature_authentication.presentation.util.Screen
import com.jlahougue.dndcompanion.ui.spacing
import com.jlahougue.dndcompanion.ui.theme.DnDCompanionTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val state = viewModel.state.value
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Min)
        ) {
            Image(
                painter = painterResource(R.drawable.die),
                contentDescription = stringResource(R.string.label_logo),
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 20.dp)
            )
            OutlinedTextField(
                label = { Text(text = stringResource(R.string.label_email)) },
                value = state.email,
                onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
                modifier = Modifier
                    .padding(top = 10.dp),
                textStyle = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                label = { Text(text = stringResource(R.string.label_password)) },
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
                modifier = Modifier
                    .padding(top = 10.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            ClickableText(
                text = AnnotatedString(stringResource(R.string.text_register)),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                overflow = TextOverflow.Visible,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.medium)
            ) {
                navController.navigate(Screen.RegisterScreen.route)
            }
        }
    }
}

@Preview(
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun LoginScreenPreview() {
    DnDCompanionTheme {
        //LoginScreen()
    }
}