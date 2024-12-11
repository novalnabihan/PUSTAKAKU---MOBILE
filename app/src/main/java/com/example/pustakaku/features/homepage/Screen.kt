package com.example.pustakaku.features.homepage

import android.icu.util.IslamicCalendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.components.LoadingView
import com.example.pustakaku.features.homepage.components.BookCard
import com.example.pustakaku.features.homepage.components.DynamicCard
import com.example.pustakaku.features.homepage.components.GamifiedCard
import com.example.pustakaku.features.homepage.components.GreetingText
import com.example.pustakaku.features.homepage.components.SearchBar
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

@Composable
fun HomeScreen(navController: NavController, dataViewModel: DataViewModel = viewModel()) {
  val genres = dataViewModel.genres.value
  val books = dataViewModel.books.value
  val userName = dataViewModel.userName.value
  val bookProgress by dataViewModel.getBooksProgress().observeAsState(initial = DataViewModel.BookProgress(0, 0))
  val isLoading by dataViewModel.isLoading.observeAsState(initial = true)

  LaunchedEffect(Unit) {
    dataViewModel.loadUserName()
  }

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = colorResource(id = R.color.wheat)
  ) {
    LazyVerticalGrid(
      columns = GridCells.Fixed(2), // Dua kolom per baris
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      // Elemen pertama: Search Bar
      item(span = { GridItemSpan(maxLineSpan) }) {
        Spacer(modifier = Modifier.height(20.dp))
        SearchBar()
        Spacer(modifier = Modifier.height(24.dp))
      }

      // Elemen kedua: Greeting Text
      item(span = { GridItemSpan(maxLineSpan) }) {
        GreetingText(name = userName)
      }

      // Elemen ketiga: GamifiedCard
      item(span = { GridItemSpan(maxLineSpan) }) {
        if (isLoading) {
          LoadingView()
        } else {
          if (bookProgress.totalBooks == 0) {
            Text("Kamu belum mulai membaca. Mulai sekarang Yuk!")
          } else {
            GamifiedCard(
              totalBooks = bookProgress.totalBooks,
              booksRead = bookProgress.booksRead
            )
          }
        }
      }

      // Elemen keempat: Genres (LazyRow)
      if (genres.isNotEmpty()) {
        item(span = { GridItemSpan(maxLineSpan) }) {
          LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
          ) {
            items(genres) { item ->
              DynamicCard(
                title = item.title,
                backgroundColor = Color(0xFF4DA7D3)
              )
            }
          }
        }
      }

      // Elemen kelima: Books (BookCard dalam grid)
      items(books) { item ->
        BookCard(
          title = item.title,
          rating = item.rating,
          author = item.author,
          likes = item.likes,
          modifier = Modifier
            .clickable {
              navController.navigate("Detail/${item.id}")
            }
        )
      }
    }
  }
}
