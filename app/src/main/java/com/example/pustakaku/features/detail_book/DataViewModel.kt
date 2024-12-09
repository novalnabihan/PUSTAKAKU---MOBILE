package com.example.pustakaku.features.detail_book

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel : ViewModel() {

  private val _detailBook = MutableStateFlow<BookDetailModel?>(null)
  val detailBook: StateFlow<BookDetailModel?> = _detailBook

  fun getData(bookId: String) {
    viewModelScope.launch {
      val bookDetail = fetchBookById(bookId)
      _detailBook.value = bookDetail
    }
  }

  private suspend fun fetchBookById(bookId: String): BookDetailModel? {
    val db = FirebaseFirestore.getInstance()
    return try {
      val snapshot = db.collection("books").document(bookId).get().await()
      val book = snapshot.toObject(BookDetailModel::class.java)

      book?.let {
        val chapters = fetchChapters(bookId)
        book.copy(chapters = chapters)
      }
    } catch (e: Exception) {
      Log.e("Error", "Error fetching book by ID: $e")
      null
    }
  }

  private suspend fun fetchChapters(bookId: String): List<ChapterModel> {
    val db = FirebaseFirestore.getInstance()
    return try {
      val snapshot = db.collection("books")
        .document(bookId).collection("chapters")
        .orderBy("page")
        .get().await()
      snapshot.documents.mapNotNull { doc ->
        doc.toObject(ChapterModel::class.java)?.copy(id = doc.id)
      }
    } catch (e: Exception) {
      Log.e("Error", "Error fetching chapters: $e")
      emptyList()
    }
  }
}

