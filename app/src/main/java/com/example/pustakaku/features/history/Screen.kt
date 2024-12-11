package com.example.pustakaku.features.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.components.EmptyView
import com.example.pustakaku.components.LoadingView
import com.example.pustakaku.features.history.components.HistoryItem
import com.example.pustakaku.features.history.components.TitlePage
import com.example.pustakaku.features.homepage.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryPage(navController: NavController) {
    val viewModel: DataViewModel = viewModel()
    val historyBacaan by viewModel.getHistoryBacaan().observeAsState(initial = emptyList())
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    Scaffold(
        containerColor = colorResource(id = R.color.wheat),
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                TitlePage(title = "Bacaan Anda Selama ini")
                SearchBar()
                Spacer(modifier = Modifier.height(24.dp))

                when {
                    isLoading -> {
                        LoadingView()
                    }

                    historyBacaan.isEmpty() -> {
                        EmptyView("History masih kosong!")
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(historyBacaan) { history ->
                                HistoryItem(history)
                            }
                        }
                    }
                }
            }
        }
    )
}
