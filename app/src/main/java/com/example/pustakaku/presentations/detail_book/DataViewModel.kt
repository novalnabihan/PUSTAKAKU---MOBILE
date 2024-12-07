package com.example.pustakaku.presentations.detail_book

import com.example.pustakaku.presentations.homepage.BookModel
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel: ViewModel(){
  val detailBook = mutableStateOf<BookModel?>(null)

  fun getData(bookId: String){
    viewModelScope.launch {
      detailBook.value = fetchBookById(bookId)
    }
  }

  private suspend fun fetchBookById(bookId: String): BookModel? {
    val db = FirebaseFirestore.getInstance()
    return try {
      val snapshot = db.collection("books").document(bookId).get().await()
      snapshot.toObject(BookModel::class.java)
    } catch (e: Exception) {
      Log.d("error", "Error fetching book by ID: $e")
      null
    }
  }
}


