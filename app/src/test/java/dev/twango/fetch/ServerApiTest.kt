package dev.twango.fetch

import dev.twango.fetch.data.ServerApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

class ServerApiTest {

    /**
     * Test fetching items from [ServerApi.PRIMARY_ENDPOINT] and ensure that the list is not empty.
     */
    @Test
    fun fetchItems() = runBlocking {
        val items = ServerApi.fetchItems()
        assertTrue(items.isNotEmpty())
    }


}