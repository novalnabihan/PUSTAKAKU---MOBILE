package com.example.pustakaku.utils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(millis: Long): String {
  val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm:ss", Locale.getDefault())
  return sdf.format(Date(millis))
}

