package com.jlahougue.dndcompanion.data_ability.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability

@Dao
interface AbilityRoomDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ability: Ability)

    @Query("DELETE FROM ability WHERE cid = :characterID")
    suspend fun deleteForCharacter(characterID: Long)
}