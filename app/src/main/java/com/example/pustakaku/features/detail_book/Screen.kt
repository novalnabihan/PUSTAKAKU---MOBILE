package com.example.pustakaku.features.detail_book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun BookDetailScreen(
  navController: NavController, bookId: String
) {
  val viewModel: DataViewModel = viewModel()
  val book = viewModel.detailBook.value

  LaunchedEffect(bookId) {
    viewModel.getData(bookId)
  }

  if (book == null) {
    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
  } else {
    Column(modifier = Modifier.padding(16.dp)) {
      Text(text = "Title: ${book.title}")
      Text(text = "Author: ${book.author}")
      Text(text = "Description: ${book.description}")
      Button(onClick = { navController.popBackStack() }) {
        Text(text = "Back")
      }
    }
  }
}

