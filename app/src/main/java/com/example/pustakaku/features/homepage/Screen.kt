package com.example.pustakaku.features.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pustakaku.R
import com.example.pustakaku.features.homepage.components.BookCard
import com.example.pustakaku.features.homepage.components.DynamicCard
import com.example.pustakaku.features.homepage.components.GamifiedCard
import com.example.pustakaku.features.homepage.components.GreetingText
import com.example.pustakaku.features.homepage.components.SearchBar

@Composable
fun HomeScreen(navController: NavController, dataViewModel: DataViewModel = viewModel()) {
  val genres = dataViewModel._genres.value
  val books = dataViewModel._books.value
  val userName = dataViewModel.userName.value

  Surface(
    modifier = Modifier.fillMaxSize(),
    color = colorResource(id = R.color.wheat)
  ) {
    Column(
      modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
      SearchBar()
      Spacer(modifier = Modifier.height(24.dp))

      LaunchedEffect(Unit) {
        dataViewModel.loadUserName()
      }
      GreetingText(name = userName)
      GamifiedCard(totalBooks = 10, booksRead = 3)
      Spacer(modifier = Modifier.height(24.dp))

      if (genres.isEmpty() or books.isEmpty()) {
        Box(
          modifier = Modifier
            .fillMaxSize(),
          contentAlignment = Alignment.Center
        ) {
          CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
          )
        }
      } else {
        LazyRow(
          modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
          contentPadding = PaddingValues(vertical = 16.dp),
          horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
          items(genres) { item ->
            DynamicCard(
              icon = Icons.Filled.Share,
              title = item.title,
              backgroundColor = Color(0xFF4DA7D3)
            )
          }
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
          modifier = Modifier
            .fillMaxWidth()
        ) {
          items(books) { item ->
            BookCard(
              title = item.title,
              rating = item.rating,
              author = item.author,
              likes = item.likes,
              modifier = Modifier
                .padding(horizontal = 8.dp)
                .widthIn(max = 200.dp, min = 150.dp)
                .clickable {
                  navController.navigate("Detail/${item.id}")
                },

              )
          }
        }
        Spacer(modifier = Modifier.height(16.dp))
      }

    }
  }
}

//@Preview
//@Composable
//private fun HomeScreenPrev() {
//  HomeScreen()
//}