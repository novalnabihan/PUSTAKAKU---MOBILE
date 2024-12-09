package com.example.pustakaku.features.detail_book

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.components.HtmlText
import com.example.pustakaku.features.detail_book.components.BookDetailTopBar
import com.example.pustakaku.features.detail_book.components.ChapterItem
import com.example.pustakaku.features.detail_book.components.HeaderBookDetail
import com.example.pustakaku.ui.theme.BlueBackground
import com.example.pustakaku.ui.theme.BlueText
import com.example.pustakaku.ui.theme.LabelApp
import com.example.pustakaku.ui.theme.PrimaryButton

@Composable
fun BookDetailScreen(
  navController: NavController, bookId: String
) {
  val viewModel: DataViewModel = viewModel()
  val book by viewModel.detailBook.collectAsState()
  var selectedTabIndex by remember { mutableStateOf(0) }

  LaunchedEffect(bookId) {
    viewModel.getData(bookId)
  }

  if (book == null) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.wheat)),
      contentAlignment = Alignment.Center,
    ) {
      CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant
      )
    }
  } else {
    Scaffold(
      bottomBar = { /* Bottom bar */ },
    ) { paddingValues ->
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues)
          .background(color = colorResource(id = R.color.wheat))
      ) {
        HeaderBookDetail(
          title = book!!.title,
          author = book!!.author,
          episodeCount = 3,
          rating = "${book!!.rating}",
          imageRes = R.drawable.kancil,
          navController = navController
        )

        val tabs = listOf("Informasi", "Chapters")
        TabRow(selectedTabIndex = selectedTabIndex, Modifier.background(color = Color(0xFFCC9933))) {
          tabs.forEachIndexed { index, title ->
            Tab(
              selected = selectedTabIndex == index,
              onClick = { selectedTabIndex = index },
              text = { Text(title) }
            )
          }
        }

        when (selectedTabIndex) {
          0 -> { // Information Tab
            Column(modifier = Modifier.padding(16.dp)) {
              LabelApp(text = "Fabel", backgroundColor = BlueBackground, textColor = BlueText)
              Spacer(modifier = Modifier.height(20.dp))
              HtmlText(html = book!!.description, fontSize = 16f)
              Spacer(modifier = Modifier.height(40.dp))
              PrimaryButton(onClick = {
                navController.navigate("Detail/${bookId}/read")
              }, text = "Baca Sekarang!", fullWidth = true)
            }
          }

          1 -> { // Chapters Tab
            LazyColumn(
              modifier = Modifier.padding(16.dp),
              verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
              items(book!!.chapters) { chapter ->
                ChapterItem(
                  chapterTitle = chapter.title,
                  onClick = {}
                )
              }
            }
          }
        }
      }
    }
  }
}


