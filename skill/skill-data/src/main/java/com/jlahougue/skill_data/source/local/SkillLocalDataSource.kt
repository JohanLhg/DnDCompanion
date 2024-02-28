package com.jlahougue.skill_data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.skill_domain.model.Skill

@Dao
interface SkillLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skill: Skill)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skills: List<Skill>)

    @Query("DELETE FROM skill WHERE cid = :characterID")
    suspend fun delete(characterID: Long)
}