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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel
) {
    val state = viewModel.state.value
    Box(
        contentAlignment = Alignment.Center,
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
                    .padding(bottom = 20.dp)
            )
            OutlinedTextField(
                label = { Text(text = stringResource(R.string.label_email)) },
                value = state.email,
                onValueChange = { viewModel.onEvent(RegisterEvent.EmailChanged(it)) },
                modifier = Modifier
                    .padding(top = 10.dp),
                textStyle = MaterialTheme.typography.bodyMedium
            )
            OutlinedTextField(
                label = { Text(text = stringResource(R.string.label_password)) },
                value = state.password,
                onValueChange = { viewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
                modifier = Modifier
                    .padding(top = 10.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            OutlinedTextField(
                label = { Text(text = stringResource(R.string.label_confirm_password)) },
                value = state.confirmPassword,
                onValueChange = { viewModel.onEvent(RegisterEvent.ConfirmPasswordChanged(it)) },
                modifier = Modifier
                    .padding(top = 10.dp),
                textStyle = MaterialTheme.typography.bodyMedium,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            ClickableText(
                text = AnnotatedString(stringResource(R.string.text_login)),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                overflow = TextOverflow.Visible,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.medium)
            ) {
                navController.navigate(Screen.LoginScreen.route)
            }
        }
    }
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