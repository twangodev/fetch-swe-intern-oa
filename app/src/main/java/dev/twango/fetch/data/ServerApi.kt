package dev.twango.fetch.data

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object ServerApi {

    private const val TAG = "ServerApi"

    private const val PRIMARY_ENDPOINT = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun fetchItems(): List<Item> {
        Log.d(TAG, "Fetching items from $PRIMARY_ENDPOINT")
        return client.get(PRIMARY_ENDPOINT).body()
    }

    /**
     * Groups items by listId and sorts them by id. The groups are then sorted by listId and converted to a list for compatibility with [LazyColumn].
     */
    fun groupByListId(items: List<Item>): List<Pair<Int, List<Item>>> {
        val filteredItems = items.filter { !it.name.isNullOrBlank() }
        val map = filteredItems.groupBy { it.listId }

        val sortedMap = mutableMapOf<Int, List<Item>>()
        map.forEach { (listId, items) -> // Populate sortedMap with items sorted by id
            sortedMap[listId] = items.sortedBy { it.id }
        }

        Log.v(TAG, "Sorted map: $sortedMap")

        val groups = sortedMap.toList()
        return groups.sortedBy { it.first } // Sort groups (listIds)
    }

}