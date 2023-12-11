package com.jlahougue.dndcompanion.data_ability.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import kotlinx.coroutines.flow.Flow

@Dao
interface AbilityLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ability: Ability)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(abilities: List<Ability>)

    @Query("DELETE FROM ability WHERE cid = :characterID")
    suspend fun deleteForCharacter(characterID: Long)

    @Query(
        """
            SELECT * 
            FROM ability_view 
            WHERE cid = :characterID 
            ORDER BY name = 'CHA', 
            name = 'WIS', 
            name = 'INT',
            name = 'CON',
            name = 'DEX',
            name = 'STR'
        """
    )
    fun get(characterID: Long): Flow<List<AbilityView>>
}