package com.jlahougue.damage_type_presentation

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.components.containers.CustomDialog
import com.jlahougue.core_presentation.theme.DnDCompanionTheme
import com.jlahougue.core_presentation.theme.spacing
import com.jlahougue.damage_type_domain.model.DamageType

@Composable
fun DamageTypeDialog(
    state: DamageTypeDialogState,
    onEvent: (DamageTypeDialogEvent) -> Unit
) {
    val damageType = state.damageType ?: return
    CustomDialog(
        isShown = state.isShown,
        onDismissRequest = { onEvent(DamageTypeDialogEvent.OnDismiss) },
        modifier = Modifier
            .width(IntrinsicSize.Max),
        title = damageType.name
    ) {
        Text(
            text = damageType.description,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
        )
    }
}

@Preview
@Composable
private fun DamageTypeDialogPreview() {
    DnDCompanionTheme {
        DamageTypeDialog(
            state = DamageTypeDialogState(
                isShown = true,
                damageType = DamageType(
                    name = "Fire",
                    description = "This is a fire damage type"
                )
            ),
            onEvent = {}
        )
    }
}