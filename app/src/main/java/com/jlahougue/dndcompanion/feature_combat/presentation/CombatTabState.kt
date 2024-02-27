package com.jlahougue.dndcompanion.feature_combat.presentation

import com.jlahougue.character_spell_domain.model.SpellLevel
import com.jlahougue.dndcompanion.data_item.presentation.InventoryState
import com.jlahougue.dndcompanion.data_weapon.presentation.WeaponState
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabItem
import com.jlahougue.dndcompanion.feature_combat.presentation.component.TabState

data class CombatTabState(
    override val tabs: List<TabItem>,
    override val selectedTabIndex: Int,
    val unitSystem: com.jlahougue.settings_domain.model.UnitSystem = com.jlahougue.settings_domain.model.UnitSystem.METRIC,
    val weapons: WeaponState = WeaponState(),
    val inventory: InventoryState = InventoryState(),
    val spells: List<SpellLevel> = emptyList()
) : TabState(tabs, selectedTabIndex)
