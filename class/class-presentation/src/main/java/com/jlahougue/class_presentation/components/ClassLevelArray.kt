package com.jlahougue.class_presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassLevelStats
import com.jlahougue.class_presentation.R
import com.jlahougue.core_domain.util.extension.toSignedString
import com.jlahougue.core_presentation.components.table.DataTable
import com.jlahougue.core_presentation.theme.DnDCompanionTheme

@Composable
fun ClassLevelArray(
    levels: List<ClassLevelStats>,
    modifier: Modifier = Modifier
) {
    val headers = mutableListOf(
        stringResource(id = R.string.level),
        stringResource(id = R.string.ability_score_bonus),
        stringResource(id = R.string.proficiency_bonus)
    )
    val hasCantrips = levels.any { it.level.cantripsKnown > 0 }
    if (hasCantrips) {
        headers.add(stringResource(id = R.string.cantrips_known))
    }
    val hasSpells = levels.any { it.level.spellsKnown > 0 }
    if (hasSpells) {
        headers.add(stringResource(id = R.string.spells_known))
    }
    for (i in 1..9) {
        if (levels.any { it.spellSlots.containsKey(i) }) {
            headers.add(i.toString())
        }
    }
    val rows = mutableListOf<List<String>>()
    levels.forEach { level ->
        val row = mutableListOf(
            level.level.level.toString(),
            level.level.abilityScoreBonuses.toSignedString(),
            level.level.profBonus.toSignedString()
        )
        if (hasCantrips) {
            row.add(level.level.cantripsKnown.toString())
        }
        if (hasSpells) {
            row.add(level.level.spellsKnown.toString())
        }
        for (i in 1..9) {
            if (level.spellSlots.containsKey(i)) {
                row.add(level.spellSlots[i].toString())
            }
            else {
                row.add(stringResource(id = com.jlahougue.core_presentation.R.string.empty))
            }
        }
        rows.add(row)
    }
    DataTable(
        headers = headers,
        rows = rows,
        modifier = modifier
    )
}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
private fun ClassLevelArrayPreview() {
    DnDCompanionTheme {
        ClassLevelArray(
            levels = listOf(
                ClassLevelStats(
                    level = ClassLevel(
                        clazz = "Wizard",
                        level = 1,
                        abilityScoreBonuses = 2,
                        profBonus = 2,
                        cantripsKnown = 2,
                        spellsKnown = 2
                    ),
                    spellSlots = mapOf(1 to 2, 2 to 3, 3 to 4, 6 to 5)
                ),
            )
        )
    }
}