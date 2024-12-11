package com.example.pustakaku.features.history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.example.pustakaku.features.history.UserBookHistory
import com.example.pustakaku.R
import com.example.pustakaku.utils.formatDate

@Composable
fun HistoryItem(history: UserBookHistory) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 4.dp),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Row(
      modifier = Modifier
        .background(color = colorResource(id = R.color.medium_gray))
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column(
        modifier = Modifier
          .weight(1f)
          .padding(end = 16.dp)
      ) {
        Text(
          text = history.bookDetail?.title ?: "Judul Tidak Tersedia",
          style = TextStyle(
            color = colorResource(id = R.color.wheat),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
          )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
          text = "Terakhir Dilihat: ${formatDate(history.lastSeen)}",
          style = TextStyle(
            color = colorResource(id = R.color.wheat),
            fontSize = 10.sp
          )
        )
      }

      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.size(48.dp)
      ) {
        CircularProgressIndicator(
          progress = { history.progress / 100f },
          modifier = Modifier.size(48.dp),
          color = Color.Green,
          trackColor = colorResource(id = R.color.orange),
          strokeWidth = 4.dp
        )
        Text(
          text = "${history.progress}%",
          style = TextStyle(
            color = colorResource(id = R.color.wheat),
            fontSize = 10.sp
          )
        )
      }
    }
  }
}
@Composable
fun TitlePage(
  title: String,
  modifier: Modifier = Modifier,
  fontSize: TextUnit = 24.sp,
  fontWeight: FontWeight = FontWeight.Bold
) {
  Text(
    text = title,
    style = TextStyle(
      fontSize = fontSize,
      fontWeight = fontWeight
    ),
    modifier = modifier.padding(bottom = 16.dp)
  )
}