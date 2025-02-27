package dev.twango.fetch.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.twango.fetch.data.Item
import dev.twango.fetch.data.ServerApi.groupByListId
import dev.twango.fetch.ui.ItemViewModel
import kotlinx.coroutines.launch

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchTakeHomeTopBar() {
    TopAppBar(
        title = {
            Text(text = "Fetch Take Home")
        }
    )
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
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchTakeHomeContent(paddingValues: PaddingValues, viewModel: ItemViewModel = viewModel()) {

    val itemList = viewModel.items.value
    val groups = groupByListId(itemList)

    PullToRefreshBox(
        onRefresh = {
            viewModel.viewModelScope.launch {
                viewModel.updateItems()
            }
        },
        isRefreshing = viewModel.isRefreshing.value,
    ) {

        LazyColumn(contentPadding = paddingValues) {
            items(groups) { group ->
                ListGroup(group)
            }
        }
    }

}



