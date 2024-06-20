package com.jlahougue.skill_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.skill_domain.model.Skill
import com.jlahougue.skill_domain.model.SkillView
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skill: Skill)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skills: List<Skill>)

    @Query("DELETE FROM skill")
    suspend fun clear()

    @Query("DELETE FROM skill WHERE cid = :characterID")
    suspend fun delete(characterID: Long)

    @Query("SELECT * FROM skill_view WHERE cid = :characterId")
    fun getSkills(characterId: Long): Flow<List<SkillView>>
}