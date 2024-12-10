package com.example.pustakaku.features.chapter_content

import com.example.pustakaku.features.detail_book.DataViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.components.HtmlText
import com.example.pustakaku.features.detail_book.components.HeaderBookDetail
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun ReadingBookScreen(
  navController: NavController, bookId: String
) {
  val viewModel: DataViewModel = viewModel()
  val book by viewModel.detailBook.collectAsState()

  val currentPage = remember { mutableStateOf(0) }
  val pagerState = rememberPagerState(initialPage = 0, pageCount = { book?.chapters?.size ?: 0 })
  val scope = rememberCoroutineScope()

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

        Box(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 30.dp)
        ) {
          HorizontalPager(
            state = pagerState,
            key = { book!!.chapters[it].id },
            pageSize = PageSize.Fill
          ) { index ->
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
              item {
                Text(
                  text = book!!.chapters[index].title,
                  style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                )
              }
              item {
                HtmlText(html = book!!.chapters[index].content, fontSize = 16f)
              }
            }
          }

          Row(
            modifier = Modifier
              .align(Alignment.BottomCenter)
              .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            // Tombol Prev
            IconButton(
              onClick = {
                scope.launch {
                  pagerState.animateScrollToPage(
                    pagerState.currentPage - 1
                  )
                  currentPage.value = maxOf(currentPage.value, pagerState.currentPage)
                }
              },
              enabled = pagerState.currentPage > 0
            ) {
              Box(
                modifier = Modifier
                  .size(48.dp)
                  .clip(CircleShape)
                  .background(
                    color = if (pagerState.currentPage > 0) colorResource(id = R.color.dark_gray)
                    else Color.Transparent
                  ),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.KeyboardArrowLeft,
                  contentDescription = "Go back",
                  tint = if (pagerState.currentPage > 0) Color.White else Color.Transparent
                )
              }
            }

            // Tombol Next
            IconButton(
              onClick = {
                scope.launch {
                  pagerState.animateScrollToPage(
                    pagerState.currentPage + 1
                  )
                  currentPage.value = maxOf(currentPage.value, pagerState.currentPage)
                }
              },
              enabled = pagerState.currentPage < pagerState.pageCount - 1
            ) {
              Box(
                modifier = Modifier
                  .size(48.dp)
                  .clip(CircleShape)
                  .background(
                    color = if (pagerState.currentPage < pagerState.pageCount - 1) colorResource(id = R.color.dark_gray)
                    else Color.Transparent
                  ),
                contentAlignment = Alignment.Center
              ) {
                Icon(
                  imageVector = Icons.Default.KeyboardArrowRight,
                  contentDescription = "Go forward",
                  tint = if (pagerState.currentPage < pagerState.pageCount - 1) Color.White else Color.Transparent
                )
              }
            }
          }
        }
      }
    }
  }
  LaunchedEffect(currentPage.value) {
    if (book != null) {
      val totalChapters = book?.chapters?.size ?: 0
      viewModel.saveOrUpdateUserBook(bookId, currentPage.value, totalChapters)
    }
  }
}


