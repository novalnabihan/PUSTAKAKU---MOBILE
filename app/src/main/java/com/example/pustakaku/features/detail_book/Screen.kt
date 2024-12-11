package com.example.pustakaku.features.detail_book

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.components.HtmlText
import com.example.pustakaku.components.LoadingView
import com.example.pustakaku.features.detail_book.components.ChapterItem
import com.example.pustakaku.features.detail_book.components.HeaderBookDetail
import com.example.pustakaku.ui.theme.BlueBackground
import com.example.pustakaku.ui.theme.BlueText
import com.example.pustakaku.ui.theme.LabelApp
import com.example.pustakaku.ui.theme.OrangeBackground
import com.example.pustakaku.ui.theme.PrimaryButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun BookDetailScreen(
  navController: NavController,
  bookId: String,

) {
  val viewModel: DataViewModel = viewModel()
  val book by viewModel.detailBook.collectAsState()

  var selectedTabIndex by remember { mutableStateOf(0) }

  LaunchedEffect(bookId) {
    viewModel.getData(bookId)
    viewModel.getUid()
  }

  if (book == null) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.wheat)),
      contentAlignment = Alignment.Center,
    ) {
      LoadingView()
    }
  } else {
    Scaffold(
      bottomBar = {
        Row(
          modifier = Modifier
            .background(
              color = colorResource(id = R.color.wheat)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
          PrimaryButton(
            onClick = {
              navController.navigate("Detail/$bookId/read")
              book?.let {
                val totalChapters = it.chapters.size
                CoroutineScope(Dispatchers.IO).launch {
                  viewModel.saveOrUpdateUserBook(bookId, 0, totalChapters)
                }
              }
            },
            text = "Baca Sekarang!",
            fullWidth = true
          )
        }
      }
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
          episodeCount = book!!.chapters.size,
          rating = "${book!!.rating}",
          imageRes = R.drawable.kancil,
          navController = navController
        )

        val tabs = listOf("Informasi", "Chapters")
        TabRow(
          selectedTabIndex = selectedTabIndex,
          containerColor = colorResource(id = R.color.wheat),
          contentColor = colorResource(id = R.color.dark_gray)
        ) {
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
              // Tampilkan kategori dan genre dalam satu baris
              Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Jarak antar label
                modifier = Modifier.fillMaxWidth()
              ) {
                LabelApp(
                  text = book!!.kategori,
                  backgroundColor = BlueBackground,
                  textColor = BlueText
                )

                if (!book!!.genre.isNullOrEmpty()) {
                  LabelApp(
                    text = book!!.genre,
                    backgroundColor = OrangeBackground ,
                    textColor = BlueText
                  )
                }
              }

              Spacer(modifier = Modifier.height(20.dp))

              // Tampilkan deskripsi buku
              HtmlText(html = book!!.description, fontSize = 16f)
              Spacer(modifier = Modifier.height(40.dp))
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

