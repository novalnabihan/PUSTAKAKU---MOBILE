package com.example.pustakaku.features.detail_book.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pustakaku.R

@Composable
fun HeaderBookDetail(
  imageRes: Int,
  title: String,
  author: String,
  rating: String,
  episodeCount: Int,
  modifier: Modifier = Modifier,
  navController: NavController
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .aspectRatio(16f / 9f)
      .background(Color.Black)
  ) {
    Image(
      painter = painterResource(id = imageRes),
      contentDescription = null,
      contentScale = ContentScale.Crop,
      modifier = Modifier.fillMaxSize()
    )

    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          Brush.verticalGradient(
            colors = listOf(Color.Transparent, Color.Black),
            startY = 300f
          )
        )
    )

    Column(modifier = Modifier
      .align(Alignment.TopStart)
      .padding(16.dp)) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          tint = Color.White,
          contentDescription = "Back",
          modifier = Modifier.clickable {
            navController.popBackStack()
          }
        )
        Text(
          text = "Detail Buku",
          style = MaterialTheme.typography.labelLarge,
          color = Color.White
        )
      }
    }

    // Teks di atas gambar
    Column(
      modifier = Modifier
        .align(Alignment.BottomStart)
        .padding(16.dp)
    ) {
      Text(
        text = title,
        style = TextStyle(
          color = Color.White,
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp
        )
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = "ditulis oleh $author",
        style = TextStyle(fontSize = 10.sp, color = Color(0xFFD1D1D1))
      )
      Spacer(modifier = Modifier.height(4.dp))

      Text(
        text = "$rating | $episodeCount Episode",
        style = TextStyle(fontSize = 10.sp, color = Color(0xFFD1D1D1))
      )
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookDetailTopBar(navController: NavController) {
  TopAppBar(
    title = {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        Icon(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = "Back",
          modifier = Modifier.clickable {
            navController.popBackStack()
          }
        )
        Text(
          text = "Detail Buku",
          style = MaterialTheme.typography.labelLarge
        )
      }
    },
  )
}


@Composable
fun ChapterItem(
  chapterTitle: String,
  onClick: () -> Unit
) {
  Box(
    modifier = Modifier
      .clickable { onClick() }
      .background(color = colorResource(id = R.color.white), shape = RoundedCornerShape(4.dp))
      .fillMaxWidth(),
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween) {
      Text(
        text = chapterTitle,
        color = Color.Black,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
        style = TextStyle(
          fontSize = 16.sp,
          fontWeight = FontWeight.Bold
        )
      )
      Text(
        text = "~1 Menit",
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        style = TextStyle(
          fontSize = 12.sp,
          fontWeight = FontWeight.Light
        )
      )
    }

  }
}


@Composable
fun Wrapper() {
  Column {
    ChapterItem(chapterTitle = "Malam Ini", onClick = {})
//    HeaderBookDetail(
//      title = "Betapa",
//      author = "34443",
//      episodeCount = 3,
//      rating = "3.2",
//      imageRes = R.drawable.kancil,
////      navController =
//    )

  }
}


@Preview
@Composable
private fun Preview() {
  Wrapper()
}