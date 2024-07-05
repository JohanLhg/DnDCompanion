package com.jlahougue.note.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlahougue.core_presentation.components.text_fileds.LinedTextField
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.core_presentation.util.extensions.asDp
import com.jlahougue.note.domain.model.Note

@Composable
fun NoteList(
    notes: List<Note>,
    onAddNote: () -> Unit,
    onNoteChange: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedNote by remember { mutableStateOf(notes.firstOrNull()) }
    var width by remember { mutableStateOf(100.dp) }
    val density = LocalDensity.current

    LaunchedEffect(key1 = notes) {
        if (selectedNote == null) {
            selectedNote = notes.firstOrNull()
        }
    }

    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.notes).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add_note),
                modifier = Modifier
                    .size(40.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = false),
                        onClick = onAddNote,
                    )
                    .padding(MaterialTheme.spacing.extraSmall)
            )
        }
        HorizontalDivider()
        if (notes.isEmpty()) {
            return
        }
        Row {
            LazyColumn {
                items(notes, key = { it.id }) { note ->
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .background(
                                if (note == selectedNote) MaterialTheme.colorScheme.primary
                                else Color.Transparent
                            )
                            .onSizeChanged {
                                width = it.width.asDp(density)
                            }
                            .widthIn(min = width)
                            .clickable {
                                selectedNote = note
                            }
                    ) {
                        Text(
                            text = note.title,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(MaterialTheme.spacing.small),
                            color = if (note == selectedNote) MaterialTheme.colorScheme.onPrimary
                            else MaterialTheme.colorScheme.onSurface
                        )
                        HorizontalDivider()
                    }
                }
            }
            VerticalDivider()
            if (selectedNote == null) {
                return
            }
            LinedTextField(
                value = selectedNote!!.content,
                onValueChange = {
                    onNoteChange(selectedNote!!.copy(content = it))
                },
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(MaterialTheme.spacing.small)
                    .fillMaxSize(),
                lineColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview(
    showBackground = true,
)
@Composable
private fun NoteListPreview() {
    DnDCompanionTheme {
        NoteList(
            notes = listOf(
                Note(
                    id = 1,
                    cid = 1,
                    title = "Note 1",
                    content = "Content 1\nLine 2"
                ),
                Note(
                    id = 2,
                    cid = 1,
                    title = "Note 2",
                    content = "Content 2"
                ),
                Note(
                    id = 3,
                    cid = 1,
                    title = "Note 3",
                    content = "Content 3"
                ),
                Note(
                    id = 4,
                    cid = 1,
                    title = "Note 4",
                    content = "Content 3"
                )
            ),
            onAddNote = {},
            onNoteChange = {}
        )
    }
}