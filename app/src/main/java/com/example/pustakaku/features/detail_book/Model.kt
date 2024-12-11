package com.example.pustakaku.features.detail_book

data class BookDetailModel(
  val id: String = "",
  val title: String = "",
  val author: String = "",
  val description: String = "",
  val likes: Int = 0,
  val rating: Double = 0.0,
  val views: Int = 0,
  val chapters: List<ChapterModel> = emptyList(),
  val kategori: String = "",
  val genre: String =""
)

data class ChapterModel(
  val id: String = "",
  val title: String = "",
  val content: String = "",
  val nextPage: String? = null,
  val prevPage: String? = null,
  val page:Int = 0
)

data class GenreModel(
  val name: String // Nama genre
)

