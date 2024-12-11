package com.example.pustakaku.features.homepage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pustakaku.R


@Composable
fun DynamicCard(
  icon: ImageVector,
  title: String,
  backgroundColor: Color,
  modifier: Modifier = Modifier
) {
  Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Center,
    modifier = modifier
      .size(width = 150.dp, height = 120.dp)
      .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
      .padding(16.dp)
  ) {
    // Icon
    Icon(
      imageVector = icon,
      contentDescription = null,
      tint = Color.White,

      modifier = Modifier
        .size(30.dp)
        .background(
          color = Color.White.copy(alpha = 0.2f),
          shape = CircleShape
        )
        .padding(4.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    // Title
    Text(
      text = title,
      style = TextStyle(
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
      )
    )
  }
}

@Composable
fun GreetingText(name: String) {
  Text(
    buildAnnotatedString {
      withStyle(style = SpanStyle(fontSize = 30.sp, fontWeight = FontWeight.Normal)) {
        append("Hello, ")
      }
      withStyle(style = SpanStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)) {
        append(name)
      }
    },
    modifier = Modifier.padding(bottom = 16.dp)
  )
}

@Composable
fun GamifiedCard(totalBooks: Int, booksRead: Int) {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .height(90.dp)
      .background(
        color = colorResource(id = R.color.medium_gray),
        shape = RoundedCornerShape(16.dp)
      )
      .padding(16.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.Start,
      modifier = Modifier.fillMaxSize()
    ) {
      Text(
        text = "Hebat, Lanjutkan Membaca!",
        color = Color.White,
        fontSize = 16.sp,
      )
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = "Books read  $booksRead / $totalBooks",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
      )
    }
  }
}


@Composable
fun SearchBar() {
  var textState by remember { mutableStateOf("") }
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .border( // Added border
        width = 1.2.dp,
        color = colorResource(id = R.color.medium_gray),
        shape = MaterialTheme.shapes.extraLarge
      )
      .background(
        color = colorResource(id = R.color.white),
        shape = MaterialTheme.shapes.extraLarge
      )
      .padding(horizontal = 8.dp, vertical = 4.dp), // Padding internal,

    verticalAlignment = Alignment.CenterVertically
  ) {
    // TextField
    TextField(
      value = textState,
      onValueChange = { newValue ->
        textState = newValue
      },
      placeholder = { Text("What are you looking for?") },
      modifier = Modifier
        .background(
          color = colorResource(id = R.color.white),
          shape = MaterialTheme.shapes.extraLarge
        ),
      colors = TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent
      ),
    )

    Spacer(modifier = Modifier.width(28.dp))
    IconButton(

      modifier = Modifier.background(Color((0xF5F5F5))), onClick = {
      }
    ) {

      Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search",
        tint = Color.White,
        modifier = Modifier
          .background(Color((0xFFE91E63)), shape = CircleShape)
          .padding(8.dp)
      )
    }
  }
}

@Composable
fun BookCard(
  modifier: Modifier = Modifier,
  title: String,
  author: String,
  likes: Number,
  rating: Double
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .height(410.dp)
      .padding(8.dp)
  ) {
    // Gambar buku
    MyImage(
      imageResId = R.drawable.book3,
      contentDescription = "Thumbnail",
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(3f / 4f)
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Judul buku
    Text(
      text = title,
      style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
      color = colorResource(id = R.color.dark_gray),
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.weight(0.5f)) // Spacer fleksibel

    // Informasi author, likes, dan rating
    Column {
      Text(
        text = "by $author",
        style = MaterialTheme.typography.bodyMedium,
        color = colorResource(id = R.color.medium_gray),
        modifier = Modifier.fillMaxWidth()
      )

      Spacer(modifier = Modifier.height(6.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        // Likes
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            painter = painterResource(id = R.drawable.hearth),
            contentDescription = "Likes",
            modifier = Modifier.size(18.dp),
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "${formatLikesCount(likes)}", // Gunakan fungsi utilitas
            style = MaterialTheme.typography.bodyMedium
          )
        }

        // Rating
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            painter = painterResource(id = R.drawable.star),
            contentDescription = "Rating",
            modifier = Modifier.size(18.dp),
            tint = colorResource(id = R.color.dark_gray)
          )
          Spacer(modifier = Modifier.width(4.dp))
          Text(
            text = "$rating/5",
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }
    }
  }
}

fun formatLikesCount(likes: Number): String {
  return when {
    likes.toLong() >= 1_000_000 -> "${likes.toLong() / 1_000_000}M" // Jutaan
    likes.toLong() >= 1_000 -> "${likes.toLong() / 1_000}K" // Ribuan
    else -> likes.toString() // Di bawah 1.000, tampilkan angka asli
  }
}


@Composable
fun MyImage(
  imageResId: Int,
  contentDescription: String,
  modifier: Modifier = Modifier,
  contentScale: ContentScale = ContentScale.Crop
) {
  Image(
    painter = painterResource(id = imageResId),
    contentDescription = contentDescription,
//    modifier = modifier,
    contentScale = contentScale
  )
}