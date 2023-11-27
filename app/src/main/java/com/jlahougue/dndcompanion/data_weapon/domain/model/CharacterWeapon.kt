package com.jlahougue.dndcompanion.data_weapon.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = CharacterWeapon.TABLE_CHARACTER_WEAPON,
    primaryKeys = [CharacterWeapon.CHARACTER_WEAPON_CID, CharacterWeapon.CHARACTER_WEAPON_NAME]
)
data class CharacterWeapon(
    @ColumnInfo(name = CHARACTER_WEAPON_CID)
    var cid: Long = 0,
    @ColumnInfo(name = CHARACTER_WEAPON_NAME)
    var name: String = "",
    @ColumnInfo(name = CHARACTER_WEAPON_COUNT)
    var count: Int = 0,
    @ColumnInfo(name = CHARACTER_WEAPON_PROFICIENCY)
    var proficiency: Boolean = false
) {
    companion object {
        const val TABLE_CHARACTER_WEAPON = "character_weapon"
        const val CHARACTER_WEAPON_CID = "cid"
        const val CHARACTER_WEAPON_NAME = "name"
        const val CHARACTER_WEAPON_COUNT = "count"
        const val CHARACTER_WEAPON_PROFICIENCY = "proficiency"
    }
}