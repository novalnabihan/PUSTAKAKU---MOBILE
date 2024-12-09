package com.example.pustakaku.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButton(
  onClick: () -> Unit,
  text: String,
  fullWidth: Boolean = false,
  fontSize: TextUnit = 14.sp,
) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A2438)),
    shape = MaterialTheme.shapes.small,
    modifier = Modifier.then(
      if (fullWidth) Modifier.fillMaxWidth() else Modifier
    )
  ) {
    Text(
      text = text,
      color = Color.White,
      fontSize = fontSize,
      fontWeight = FontWeight.Bold
    )
  }
}


@Composable
fun OutlinedPrimaryButton(onClick: () -> Unit, text : String) {
  OutlinedButton(
    onClick = onClick,
    border = BorderStroke(1.dp, Color(0xFF2A2438)),
    shape = MaterialTheme.shapes.medium
  ) {
    Text(
      text = text,
      color = Color(0xFFFF6500),
      fontSize = 14.sp,
      fontWeight = FontWeight.Bold
    )
  }
}

@Composable
fun Wrapper() {
  Column {
    PrimaryButton(onClick = { /* TODO */ }, text ="Selengkapnya")
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedPrimaryButton(onClick = { /* TODO */ }, text ="Cupcupcupupcp")
  }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
  Wrapper()
}