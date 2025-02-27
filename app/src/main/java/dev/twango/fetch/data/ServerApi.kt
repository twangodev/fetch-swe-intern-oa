package dev.twango.fetch.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

object ServerApi {

    private const val PRIMARY_ENDPOINT = "https://fetch-hiring.s3.amazonaws.com/hiring.json"

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun fetchItems(): List<Item> {
        return client.get(PRIMARY_ENDPOINT).body()
    }

}