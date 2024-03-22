package com.jlahougue.character_domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Character.TABLE_CHARACTER)
data class Character(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = CHARACTER_ID)
    var id: Long = 0,
    @ColumnInfo(name = CHARACTER_NAME)
    var name: String = "",
    @ColumnInfo(name = CHARACTER_RACE)
    var race: String = "",
    @ColumnInfo(name = CHARACTER_CLASS)
    var clazz: String = "",
    @ColumnInfo(name = CHARACTER_LEVEL)
    var level: Int = 1,
    @ColumnInfo(name = CHARACTER_GENDER)
    var gender: String = "",
    @ColumnInfo(name = CHARACTER_AGE)
    var age: Int = 0,
    @ColumnInfo(name = CHARACTER_HEIGHT)
    var height: Double = 0.0,
    @ColumnInfo(name = CHARACTER_WEIGHT)
    var weight: Int = 0,
    @ColumnInfo(name = CHARACTER_PERSONALITY)
    var personality: String = "",
    @ColumnInfo(name = CHARACTER_IDEALS)
    var ideals: String = "",
    @ColumnInfo(name = CHARACTER_BONDS)
    var bonds: String = "",
    @ColumnInfo(name = CHARACTER_FLAWS)
    var flaws: String = "",
    @ColumnInfo(name = CHARACTER_BACKGROUND_TITLE)
    var backgroundTitle: String = "",
    @ColumnInfo(name = CHARACTER_BACKGROUND)
    var background: String = ""
) {
    override fun toString(): String {
        return """
            $id
            $name
            $race
            $clazz
            $level
            $gender
            $age
            $height
            $weight
            $personality
            $ideals
            $bonds
            $flaws
            $backgroundTitle
            $background
        """.trimIndent()
    }

    companion object {
        const val TABLE_CHARACTER = "character"
        const val CHARACTER_ID = "id"
        const val CHARACTER_NAME = "name"
        const val CHARACTER_RACE = "race"
        const val CHARACTER_CLASS = "class"
        const val CHARACTER_LEVEL = "level"
        const val CHARACTER_GENDER = "gender"
        const val CHARACTER_AGE = "age"
        const val CHARACTER_HEIGHT = "height"
        const val CHARACTER_WEIGHT = "weight"
        const val CHARACTER_PERSONALITY = "personality"
        const val CHARACTER_IDEALS = "ideals"
        const val CHARACTER_BONDS = "bonds"
        const val CHARACTER_FLAWS = "flaws"
        const val CHARACTER_BACKGROUND_TITLE = "background_title"
        const val CHARACTER_BACKGROUND = "background"
    }
}