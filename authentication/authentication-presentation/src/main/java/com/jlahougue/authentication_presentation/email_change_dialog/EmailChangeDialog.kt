package com.jlahougue.authentication_presentation.email_change_dialog

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.authentication_presentation.R
import com.jlahougue.core_presentation.components.CancelButton
import com.jlahougue.core_presentation.components.ConfirmButton
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.components.text_fileds.CustomLabeledOutlinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing

@Composable
fun EmailChangeDialog(
    state: EmailChangeDialogState,
    onEvent: (EmailChangeDialogEvent) -> Unit
) {
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(EmailChangeDialogEvent.OnDismiss) },
        modifier = Modifier
            .width(IntrinsicSize.Max),
        title = stringResource(id = R.string.email_change_dialog_title),
        content = {
            EmailChangeDialogContent(state, onEvent)
        },
        actions = {
            CancelButton(onClick = { onEvent(EmailChangeDialogEvent.OnDismiss) })
            ConfirmButton(onClick = { onEvent(EmailChangeDialogEvent.OnConfirm) })
        }
    )
}

@Composable
fun EmailChangeDialogContent(
    state: EmailChangeDialogState,
    onEvent: (EmailChangeDialogEvent) -> Unit
) {
    CustomLabeledOutlinedTextField(
        label = {
            Text(
                text = stringResource(R.string.label_new_email),
                style = MaterialTheme.typography.bodySmall
            )
        },
        value = state.email,
        onValueChange = { onEvent(EmailChangeDialogEvent.OnEmailChange(it)) },
        isError = !state.isEmailValid,
        modifier = Modifier
            .height(40.dp)
            .padding(horizontal = MaterialTheme.spacing.small),
        textStyle = MaterialTheme.typography.bodySmall,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
    CustomLabeledOutlinedTextField(
        label = {
            Text(
                text = "Password",
                style = MaterialTheme.typography.bodySmall
            )
        },
        value = state.password,
        onValueChange = { onEvent(EmailChangeDialogEvent.OnPasswordChange(it)) },
        isError = !state.isPasswordValid,
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.extraSmall)
            .height(40.dp)
            .padding(horizontal = MaterialTheme.spacing.small),
        textStyle = MaterialTheme.typography.bodySmall,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onEvent(EmailChangeDialogEvent.OnConfirm) }
        )
    )
}

@Preview
@Composable
private fun EmailChangeDialogPreview() {
    DnDCompanionTheme {
        EmailChangeDialog(
            state = EmailChangeDialogState(
                isShown = true,
                email = "test@test.com",
                isEmailValid = true,
                password = "password",
                isPasswordValid = true
            ),
            onEvent = {}
        )
    }
}