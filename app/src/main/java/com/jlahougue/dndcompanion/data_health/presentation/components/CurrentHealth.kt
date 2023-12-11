package com.jlahougue.dndcompanion.data_health.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_health.domain.model.Health

@Composable
fun CurrentHealth(
    health: Health,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        DividerTitle(
            title = stringResource(R.string.health_current),
            modifier = Modifier.fillMaxWidth()
        )
        TextFieldWithIncrements(
            value = health.currentHp.toString(),
            onValueChange = {},
            onPlusClick = { /*TODO*/ },
            plusDescription = stringResource(id = R.string.health_current_add),
            onMinusClick = { /*TODO*/ },
            minusDescription = stringResource(id = R.string.health_current_subtract)
        )
    }
}