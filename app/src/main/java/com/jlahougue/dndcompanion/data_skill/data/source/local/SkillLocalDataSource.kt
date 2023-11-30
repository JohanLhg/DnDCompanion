package com.jlahougue.dndcompanion.data_skill.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.jlahougue.dndcompanion.data_skill.domain.model.Skill

@Dao
interface SkillLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skill: Skill)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skills: List<Skill>)
}