package com.example.pustakaku.features.history

import com.example.pustakaku.features.detail_book.BookDetailModel

data class UserBookHistory(
  val bookId: String,
  val progress: Int,
  val status: Int,
  val lastSeen: Long,
  val bookDetail: BookDetailModel?
)