package com.example.pustakaku.features.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.pustakaku.features.detail_book.BookDetailModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.Query

class DataViewModel : ViewModel() {

  private val db = FirebaseFirestore.getInstance()
  private val auth = FirebaseAuth.getInstance()

  private val _isLoading = MutableLiveData(false)
  val isLoading: LiveData<Boolean> = _isLoading

  fun getHistoryBacaan() = liveData {
    _isLoading.postValue(true)
    val userId = auth.currentUser?.uid
    if (userId == null) {
      _isLoading.postValue(false)
      emit(emptyList<UserBookHistory>())
      return@liveData
    }

    try {
      val userBooksSnapshot = db.collection("user_books")
        .whereEqualTo("user_id", userId)
        .get()
        .await()

      val userBookHistories = userBooksSnapshot.documents.mapNotNull { document ->
        val bookId = document.getString("book_id") ?: return@mapNotNull null
        val progress = document.getLong("progress")?.toInt() ?: 0
        val status = document.getLong("status")?.toInt() ?: 0
        val lastSeen = document.getLong("last_seen") ?: 0L

        val bookDetailSnapshot = db.collection("books")
          .document(bookId)
          .get()
          .await()

        val bookDetail = if (bookDetailSnapshot.exists()) {
          BookDetailModel(
            id = bookDetailSnapshot.getString("id") ?: "",
            title = bookDetailSnapshot.getString("title") ?: "",
            author = bookDetailSnapshot.getString("author") ?: "",
            description = bookDetailSnapshot.getString("description") ?: "",
            likes = bookDetailSnapshot.getLong("likes")?.toInt() ?: 0,
            rating = bookDetailSnapshot.getDouble("rating") ?: 0.0,
            views = bookDetailSnapshot.getLong("views")?.toInt() ?: 0,
            chapters = emptyList(),
            kategori = bookDetailSnapshot.getString("kategori") ?: ""
          )
        } else {
          null
        }

        UserBookHistory(
          bookId = bookId,
          progress = progress,
          status = status,
          lastSeen = lastSeen,
          bookDetail = bookDetail
        )
      }

      emit(userBookHistories)
    } catch (e: Exception) {
      emit(emptyList<UserBookHistory>())
    } finally {
      _isLoading.postValue(false) // Akhiri loading setelah semua selesai
    }
  }
}

