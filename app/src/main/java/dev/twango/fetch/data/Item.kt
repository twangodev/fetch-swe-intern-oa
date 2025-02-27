package dev.twango.fetch.data

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val listId: String,
    val name: String?
)



