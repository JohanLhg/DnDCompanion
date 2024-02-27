package com.jlahougue.dndcompanion.data_item.data.source.remote

import com.google.firebase.firestore.FieldValue
import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.item_domain.model.Item

class ItemFirebaseDataSource(
    private val dataSource: FirebaseDataSource
) : ItemRemoteDataSource {
    override suspend fun save(item: Item) {
        dataSource.updateCharacterSheet(
            item.cid,
            mapOf("items.${item.id}" to item)
        )
    }

    override suspend fun delete(item: Item) {
        dataSource.updateCharacterSheet(
            item.cid,
            mapOf("items.${item.id}" to FieldValue.delete())
        )
    }
}