package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_health.domain.model.Health

@Composable
fun TemporaryHealth(
    health: Health,
    modifier: Modifier = Modifier
) {
    DividerTitle(
        title = stringResource(R.string.health_temporary),
        modifier = Modifier.fillMaxWidth()
    )
    TextFieldWithIncrements(
        value = health.temporaryHp.toString(),
        onValueChange = {},
        onPlusClick = { /*TODO*/ },
        plusDescription = stringResource(id = R.string.health_temporary_add),
        onMinusClick = { /*TODO*/ },
        minusDescription = stringResource(id = R.string.health_temporary_subtract),
        modifier = Modifier.fillMaxWidth()
    )
}