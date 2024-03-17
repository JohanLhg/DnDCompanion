package com.jlahougue.class_presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jlahougue.core_presentation.components.table.DataTable
import com.jlahougue.core_presentation.theme.DnDCompanionTheme

@Composable
fun ClassLevelArray() {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
            DataTable(
                headers = listOf("Level", "Ability Score Bonus", "Proficiency Bonus", "Cantrips Known", "Spells Known", "1", "2", "3", "4", "5", "6", "7", "8", "9"),
                rows = listOf(
                    listOf("1hdf fhd ghd", "+2", "+2", "2", "2", "2", "", "", "", "", "", "", "", ""),
                    listOf("2", "+2", "+2", "2", "3", "3", "", "", "", "", "", "", "", ""),
                    listOf("3", "+2", "+2", "2", "4", "4", "2", "", "", "", "", "", "", ""),
                    listOf("4", "+2", "+2", "2", "5", "4", "3", "", "", "", "", "", "", ""),
                    listOf("5", "+3", "+3", "3", "6", "4", "3", "2", "", "", "", "", "", ""),
                    listOf("6", "+3", "+3", "3", "7", "4", "3", "3", "", "", "", "", "", ""),
                    listOf("7", "+3", "+3", "3", "8", "4", "3", "3", "1", "", "", "", "", ""),
                    listOf("8", "+3", "+3", "3", "9", "4", "3", "3", "2", "", "", "", "", ""),
                    listOf("9", "+4", "+4", "4", "10", "4", "3", "3", "3", "1", "", "", "", ""),
                    listOf("10", "+4", "+4", "4", "11", "4", "3", "3", "3", "2", "", "", "", ""),
                    listOf("11", "+4", "+4", "4", "12", "4", "3", "3", "3", "2", "1", "", "", ""),
                    listOf("12", "+4", "+4", "4", "12", "4", "3", "3", "3", "2", "1", "", "", ""),
                    listOf("13", "+5", "+5", "5", "13", "4", "3", "3", "3", "2", "1", "1", "", ""),
                    listOf("14", "+5", "+5", "5", "13", "4", "3", "3", "3", "2", "1", "1", "", ""),
                )
            )
    }
}

@Composable
fun ClassLevelHeader() {

}

@Composable
fun ClassLevelRow() {

}

@Preview(
    apiLevel = 33,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
private fun ClassLevelArrayPreview() {
    DnDCompanionTheme {
        ClassLevelArray()
    }
}