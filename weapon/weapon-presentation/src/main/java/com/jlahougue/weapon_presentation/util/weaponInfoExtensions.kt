package com.jlahougue.weapon_presentation.util

import com.jlahougue.core_domain.util.extension.feetToMeterString
import com.jlahougue.user_info_domain.model.UnitSystem
import com.jlahougue.weapon_domain.model.WeaponInfo

fun WeaponInfo.getRangeString(unit: UnitSystem): String {
    var rangeStr = ""
    when (unit) {
        UnitSystem.IMPERIAL -> {
            if (range > 0) rangeStr = "$range ft."
            if (throwRangeMin > 0) {
                if (rangeStr.isNotEmpty()) rangeStr += " / "
                rangeStr += "$throwRangeMin - $throwRangeMax ft."
            }
        }
        //Metric system by default
        else -> {
            if (range > 0) rangeStr = "${range.feetToMeterString()} m"
            if (throwRangeMin > 0) {
                if (rangeStr.isNotEmpty()) rangeStr += " / "
                rangeStr += "${throwRangeMin.feetToMeterString()} - ${throwRangeMax.feetToMeterString()} m"
            }
        }
    }
    return rangeStr
}