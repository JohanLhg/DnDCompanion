package com.jlahougue.authentication_presentation.email_change_dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jlahougue.authentication_presentation.R
import com.jlahougue.core_presentation.components.CustomLabeledOutlinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.core_presentation.R as CoreR

@Composable
fun EmailChangeDialog(
    state: EmailChangeDialogState,
    onEvent: (EmailChangeDialogEvent) -> Unit
) {
    if (!state.isShown) return

    Dialog(
        onDismissRequest = {
            onEvent(EmailChangeDialogEvent.OnDismiss)
        }
    ) {
        Card(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(IntrinsicSize.Max),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )
        ) {
            Text(
                text = stringResource(id = R.string.email_change_dialog_title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .padding(
                        vertical = MaterialTheme.spacing.medium,
                        horizontal = MaterialTheme.spacing.extraSmall
                    )
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    .padding(
                        top = MaterialTheme.spacing.extraSmall,
                        bottom = MaterialTheme.spacing.small
                    )
                    .fillMaxWidth()
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
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onEvent(EmailChangeDialogEvent.OnConfirm) }
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MaterialTheme.spacing.large)
            ) {
                OutlinedButton(
                    onClick = { onEvent(EmailChangeDialogEvent.OnDismiss) },
                    shape = OutlinedTextFieldDefaults.shape,
                    contentPadding = PaddingValues(
                        horizontal = MaterialTheme.spacing.small
                    ),
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = CoreR.string.cancel).uppercase(),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }
                Button(
                    onClick = { onEvent(EmailChangeDialogEvent.OnConfirm) },
                    shape = OutlinedTextFieldDefaults.shape,
                    contentPadding = PaddingValues(
                        horizontal = MaterialTheme.spacing.small
                    ),
                    modifier = Modifier
                        .padding(bottom = MaterialTheme.spacing.extraSmall)
                        .padding(horizontal = MaterialTheme.spacing.small)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = CoreR.string.confirm).uppercase(),
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.extraSmall)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun EmailChangeDialogPreview() {
    DnDCompanionTheme {
        EmailChangeDialog(
            state = EmailChangeDialogState(isShown = true),
            onEvent = {}
        )
    }
}