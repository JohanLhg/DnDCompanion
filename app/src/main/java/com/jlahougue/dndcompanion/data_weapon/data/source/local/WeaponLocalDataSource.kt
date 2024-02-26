package com.jlahougue.dndcompanion.data_weapon.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jlahougue.weapon_domain.model.CharacterWeapon
import com.jlahougue.weapon_domain.model.Weapon
import com.jlahougue.weapon_domain.model.WeaponInfo
import com.jlahougue.weapon_domain.model.WeaponProperty
import com.jlahougue.weapon_domain.model.WeaponStats
import kotlinx.coroutines.flow.Flow

@Dao
interface WeaponLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weapon: Weapon): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperties(weaponProperties: List<WeaponProperty>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterWeapon: CharacterWeapon): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterWeapons: List<CharacterWeapon>)

    @Query("DELETE FROM character_weapon WHERE cid = :characterId")
    suspend fun delete(characterId: Long)

    @Query("SELECT weapon_name FROM weapon")
    suspend fun getNames(): List<String>

    @Transaction
    @Query("""
        SELECT
            :characterId AS cid,
            w.weapon_name AS weapon_name,
            COALESCE(cw.count, 0) AS count,
            COALESCE(cw.proficiency, 0) AS proficiency,
            w.test AS test,
            COALESCE(
                CASE 
                    WHEN cw.proficiency THEN ability.modifier + proficiency.bonus 
                    ELSE ability.modifier 
                END
            , 0) AS modifier,
            w.damage AS damage,
            w.damage_type AS damage_type,
            w.two_handed_damage AS two_handed_damage,
            w.two_handed_damage_type AS two_handed_damage_type,
            w.range AS range,
            w.throw_range_min AS throw_range_min,
            w.throw_range_max AS throw_range_max,
            w.weight AS weight,
            w.cost AS cost,
            w.description AS description
        FROM weapon w
        LEFT JOIN (
            SELECT *
            FROM character_weapon
            WHERE cid = :characterId
        ) cw ON cw.name = w.weapon_name
        LEFT JOIN proficiency_view AS proficiency ON proficiency.cid = :characterId
        LEFT JOIN ability_modifier_view AS ability ON ability.cid = :characterId AND ability.name = w.test
        WHERE w.weapon_name = :weaponName
    """)
    fun get(characterId: Long, weaponName: String): Flow<WeaponInfo>

    @Transaction
    @Query("""
        SELECT
            :characterId AS cid,
            w.weapon_name AS weapon_name,
            cw.count AS count,
            cw.proficiency AS proficiency,
            w.test AS test,
            COALESCE(
                CASE 
                    WHEN cw.proficiency THEN ability.modifier + proficiency.bonus 
                    ELSE ability.modifier 
                END
                , 0
            ) AS modifier,
            w.damage AS damage,
            w.damage_type AS damage_type,
            w.two_handed_damage AS two_handed_damage,
            w.two_handed_damage_type AS two_handed_damage_type,
            w.range AS range,
            w.throw_range_min AS throw_range_min,
            w.throw_range_max AS throw_range_max,
            w.weight AS weight,
            w.cost AS cost,
            w.description AS description
        FROM weapon w
        LEFT JOIN (
            SELECT *
            FROM character_weapon
            WHERE cid = :characterId
        ) cw ON cw.name = w.weapon_name
        LEFT JOIN proficiency_view AS proficiency ON proficiency.cid = :characterId
        LEFT JOIN ability_modifier_view AS ability ON ability.cid = :characterId AND ability.name = w.test
    """)
    fun get(characterId: Long): Flow<List<WeaponInfo>>

    @Transaction
    @Query("""
        SELECT
            :characterId AS cid,
            w.weapon_name AS weapon_name,
            cw.count AS count,
            cw.proficiency AS proficiency,
            w.test AS test,
            COALESCE(
                CASE 
                    WHEN cw.proficiency THEN ability.modifier + proficiency.bonus 
                    ELSE ability.modifier 
                END
                , 0
            ) AS modifier,
            w.damage AS damage,
            w.damage_type AS damage_type,
            w.two_handed_damage AS two_handed_damage,
            w.two_handed_damage_type AS two_handed_damage_type,
            w.range AS range,
            w.throw_range_min AS throw_range_min,
            w.throw_range_max AS throw_range_max,
            w.weight AS weight,
            w.cost AS cost,
            w.description AS description
        FROM weapon w
        INNER JOIN (
            SELECT *
            FROM character_weapon
            WHERE cid = :characterId
            AND count > 0
        ) cw ON cw.name = w.weapon_name
        LEFT JOIN proficiency_view AS proficiency ON proficiency.cid = :characterId
        LEFT JOIN ability_modifier_view AS ability ON ability.cid = :characterId AND ability.name = w.test
    """)
    fun getOwned(characterId: Long): Flow<List<WeaponInfo>>

    @Query(
        """
            SELECT 
                bonus as proficiencyBonus,
                COALESCE((
                    SELECT modifier 
                    FROM ability_view 
                    WHERE cid = :characterId 
                    AND name = '${com.jlahougue.ability_domain.model.AbilityName.STR}'
                ), 0) as strength,
                COALESCE((
                    SELECT modifier 
                    FROM ability_view 
                    WHERE cid = :characterId 
                    AND name = '${com.jlahougue.ability_domain.model.AbilityName.DEX}'
                ), 0) as dexterity
            FROM proficiency_view
            WHERE cid = :characterId
        """
    )
    fun getStats(characterId: Long): Flow<WeaponStats>
}