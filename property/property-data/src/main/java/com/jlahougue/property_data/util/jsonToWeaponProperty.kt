package com.jlahougue.property_data.util

import com.jlahougue.core_domain.util.extension.getJSONArrayIfExists
import com.jlahougue.property_domain.model.Property
import org.json.JSONObject

fun JSONObject.toWeaponProperty(): Property {
    val name = this.getString("name")
    val descArray = this.getJSONArrayIfExists("desc")
    var desc = ""
    for (i in 0 until descArray.length()) {
        if (i != 0) desc += "\n\n"
        desc += descArray.getString(i)
    }
    return Property(name, desc)
}