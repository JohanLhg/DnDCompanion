package com.jlahougue.note.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.text_fileds.LinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.note.domain.model.Note

@Composable
fun NoteDetails(
    selectedNote: Long?,
    note: Note?,
    onEditNote: (Note) -> Unit,
    onShowConfirmDialog: () -> Unit
) {
    if (note == null) return

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            NoteTitleEdit(
                selectedNote = selectedNote,
                note = note,
                onEditNote = { onEditNote(it) }
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(id = R.string.delete_note),
                tint = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .size(40.dp)
                    .padding(MaterialTheme.spacing.extraSmall)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = false),
                        onClick = onShowConfirmDialog,
                    )
            )
        }
        NoteContentEdit(
            selectedNote = selectedNote,
            note = note,
            onEditNote = { onEditNote(it) }
        )
    }
}

@Composable
fun NoteTitleEdit(
    selectedNote: Long?,
    note: Note?,
    onEditNote: (Note) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(selectedNote) {
        if (note == null) return@LaunchedEffect

        textFieldValue = textFieldValue.copy(
            text = note.title,
            selection = TextRange.Zero
        )
    }

    LaunchedEffect(note) {
        if (note == null) return@LaunchedEffect

        if (!isFocused) {
            textFieldValue = textFieldValue.copy(
                text = note.title,
                selection = TextRange.Zero
            )
        }
    }

    if (note == null) return

    Box {
        if (note.title.isEmpty()) {
            Text(
                text = stringResource(id = R.string.title_hint),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
        }
        BasicTextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onEditNote(note.copy(title = it.text))
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words
            ),
            textStyle = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(MaterialTheme.spacing.small),
            maxLines = 1
        )
    }
}

@Composable
fun NoteContentEdit(
    selectedNote: Long?,
    note: Note?,
    onEditNote: (Note) -> Unit
) {
    var textFieldValue by remember { mutableStateOf(TextFieldValue()) }
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(selectedNote) {
        if (note == null) return@LaunchedEffect

        textFieldValue = textFieldValue.copy(
            text = note.content,
            selection = TextRange.Zero
        )
    }

    LaunchedEffect(note) {
        if (note == null) return@LaunchedEffect

        if (!isFocused) {
            textFieldValue = textFieldValue.copy(
                text = note.content,
                selection = TextRange.Zero
            )
        }
    }

    if (note == null) return

    LinedTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onEditNote(note.copy(content = it.text))
        },
        interactionSource = interactionSource,
        textStyle = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(MaterialTheme.spacing.small)
            .fillMaxSize(),
        lineColor = MaterialTheme.colorScheme.secondary
    )

}

@Preview
@Composable
private fun NoteDetailsPreview() {
    DnDCompanionTheme {
        NoteDetails(
            selectedNote = 1,
            note = Note(
                id = 1,
                title = "Note title",
                content = "Note content"
            ),
            onEditNote = {},
            onShowConfirmDialog = {}
        )
    }
}