package com.jlahougue.damage_type_data.util

import com.jlahougue.damage_type_domain.model.DamageType
import org.json.JSONObject

fun JSONObject.toDamageType() = DamageType(
    name = this.getString("name"),
    description = this.getJSONArray("desc").join("\n").replace("\"", "")
)