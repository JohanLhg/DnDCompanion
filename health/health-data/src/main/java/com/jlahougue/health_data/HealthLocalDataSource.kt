package com.jlahougue.health_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jlahougue.health_domain.model.DeathSaves
import com.jlahougue.health_domain.model.Health
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(health: Health)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(deathSaves: DeathSaves)

    @Query("DELETE FROM health")
    suspend fun clearHealth()

    @Query("DELETE FROM death_saves")
    suspend fun clearDeathSaves()

    suspend fun clear() {
        clearHealth()
        clearDeathSaves()
    }

    @Query("DELETE FROM health WHERE cid = :characterID")
    suspend fun deleteHealthForCharacter(characterID: Long)

    @Query("DELETE FROM death_saves WHERE cid = :characterID")
    suspend fun deleteDeathSavesForCharacter(characterID: Long)

    suspend fun deleteForCharacter(characterID: Long) {
        deleteHealthForCharacter(characterID)
        deleteDeathSavesForCharacter(characterID)
    }

    @Query("""
        UPDATE health 
        SET current_hp = max_hp, 
        temporary_hp = 0, 
        hit_dice_used = (
            SELECT MAX(hit_dice_used - level/2, 0) 
            FROM character 
            WHERE id = :characterID
        )
        WHERE cid = :characterID
    """)
    suspend fun longRestHealth(characterID: Long)

    @Query("""
        UPDATE death_saves 
        SET successes = 0, 
        failures = 0
        WHERE cid = :characterID
    """)
    suspend fun longRestDeathSaves(characterID: Long)

    suspend fun longRest(characterID: Long) {
        longRestHealth(characterID)
        longRestDeathSaves(characterID)
    }

    @Query("SELECT * FROM health WHERE cid = :characterID")
    fun getHealth(characterID: Long): Flow<Health>

    @Query("SELECT level FROM character WHERE id = :characterID")
    fun getHitDiceNbr(characterID: Long): Flow<Int>

    @Query("SELECT * FROM death_saves WHERE cid = :characterID")
    fun getDeathSaves(characterID: Long): Flow<DeathSaves>
}