package com.jlahougue.dndcompanion.feature_combat.presentation

import com.jlahougue.character_spell_domain.model.SpellLevel
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabItem
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabState
import com.jlahougue.item_presentation.InventoryState
import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.weapon_presentation.WeaponState

data class CombatTabState(
    override val tabs: List<TabItem>,
    override val selectedTabIndex: Int,
    val unitSystem: UnitSystem = UnitSystem.METRIC,
    val weapons: WeaponState = WeaponState(),
    val inventory: InventoryState = InventoryState(),
    val spells: List<SpellLevel> = emptyList()
) : TabState(tabs, selectedTabIndex)
