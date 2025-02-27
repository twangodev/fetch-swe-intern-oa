package dev.twango.fetch.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.twango.fetch.data.Item
import dev.twango.fetch.data.ServerApi
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {

    val items = mutableStateOf<List<Item>>(emptyList())
    val isRefreshing = mutableStateOf(false)

    init {

        viewModelScope.launch {
            updateItems()
        }

    }

    suspend fun updateItems() {
        isRefreshing.value = true
        try {
            items.value = ServerApi.fetchItems()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            isRefreshing.value = false
        }
    }

}