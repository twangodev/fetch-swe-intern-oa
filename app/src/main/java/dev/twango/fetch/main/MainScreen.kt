package dev.twango.fetch.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.twango.fetch.data.Item
import dev.twango.fetch.ui.ItemViewModel

@Composable
fun FetchTakeHomeApp() {

    Scaffold(
        topBar = {
            FetchTakeHomeTopBar()
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        FetchTakeHomeContent(paddingValues)
    }

}

@Composable
fun FetchTakeHomeTopBar() {

}

@Composable
fun ListItem(item: Item) {

    Text(
        text = item.name ?: "No name",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 4.dp)
    )

}

@Composable
fun ListGroup(group: Pair<Int, List<Item>>) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "List ${group.first}",
                style = MaterialTheme.typography.headlineSmall,
            )

            Column {
                group.second.forEach { item ->
                    ListItem(item)
                }
            }
        }
    }
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

    val groups = sortedMap.toList()
    return groups.sortedBy { it.first } // Sort groups (listIds)
}

@Composable
fun FetchTakeHomeContent(paddingValues: PaddingValues, viewModel: ItemViewModel = viewModel()) {

    val itemList = viewModel.items.value
    val groups = groupByListId(itemList)

    LazyColumn(contentPadding = paddingValues) {
        items(groups) { group ->
            ListGroup(group)
        }
    }
}



