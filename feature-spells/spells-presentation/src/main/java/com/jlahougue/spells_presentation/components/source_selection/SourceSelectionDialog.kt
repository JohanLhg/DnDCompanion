package com.jlahougue.spells_presentation.components.source_selection

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.spell_domain.model.SpellSource
import com.jlahougue.spells_presentation.R

@Composable
fun SourceSelectionDialog(
    state: SourceSelectionState,
    onEvent: (SourceSelectionEvent) -> Unit
) {
    val sources = state.sources
    if (sources.isEmpty()) return
    CustomDialog(
        isShown = state.isVisible,
        onDismissRequest = { onEvent(SourceSelectionEvent.OnDismiss) },
        title = stringResource(id = R.string.source_selection_title),
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            sources.forEach { source ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEvent(SourceSelectionEvent.OnToggleSource(source)) }
                ) {
                    Switch(
                        checked = source.selected,
                        onCheckedChange = { onEvent(SourceSelectionEvent.OnToggleSource(source)) },
                        modifier = Modifier.scale(.8f)
                    )
                    Text(
                        text = source.title,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SourceSelectionDialogPreview() {
    DnDCompanionTheme {
        SourceSelectionDialog(
            state = SourceSelectionState(
                sources = listOf(
                    SpellSource(
                        title = "5e Core Rules",
                        selected = true
                    ),
                    SpellSource(
                        title = "Player's Handbook",
                        selected = true
                    ),
                    SpellSource(
                        title = "Xanathar's Guide to Everything",
                        selected = false
                    ),
                    SpellSource(
                        title = "Tasha's Cauldron of Everything",
                        selected = true
                    )
                ),
                isVisible = true
            ),
            onEvent = {}
        )
    }
}