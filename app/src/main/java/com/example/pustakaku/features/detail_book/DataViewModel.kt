package com.example.pustakaku.features.detail_book

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pustakaku.features.homepage.GenreModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel : ViewModel() {

  private val _detailBook = MutableStateFlow<BookDetailModel?>(null)
  val detailBook: StateFlow<BookDetailModel?> = _detailBook
  private val _myUid = MutableStateFlow("")
  val myUid: StateFlow<String> = _myUid
  private val _genres = MutableStateFlow<List<GenreModel>>(emptyList())
  val genres: StateFlow<List<GenreModel>> = _genres


  fun getUid() {
    viewModelScope.launch {
      val uid = FirebaseAuth.getInstance().currentUser?.uid
      _myUid.value = uid ?: ""
    }
  }

  fun getData(bookId: String) {
    viewModelScope.launch {
      val bookDetail = fetchBookById(bookId)
      _detailBook.value = bookDetail
    }
  }

  fun getGenres() {
    viewModelScope.launch {
      val db = FirebaseFirestore.getInstance()
      try {
        // Ambil semua buku dari koleksi "books"
        val snapshot = db.collection("books").get().await()

        // Ekstrak genre dari setiap buku
        val genres = snapshot.documents
          .mapNotNull { it.getString("genre") } // Ambil field "genre" jika ada
          .distinct() // Hilangkan duplikasi genre
          .map { GenreModel(it) } // Ubah menjadi GenreModel (atau tipe data yang sesuai)

        _genres.value = genres
        Log.d("Success", "Genres berhasil diambil: $genres")
      } catch (e: FirebaseFirestoreException) {
        Log.e("Error", "Gagal mengambil data genres: $e")
      }
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

  suspend fun saveOrUpdateUserBook(bookId: String, currentPage: Int, totalChapters: Int) {
    val db = FirebaseFirestore.getInstance()
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

    val docRef = db.collection("user_books").document("$userId-$bookId")
    val snapshot = docRef.get().await()

    val storedPage = snapshot.getLong("current_page")?.toInt() ?: 0
    val newPage = maxOf(currentPage + 1  , storedPage)

    Log.d("Debug", "Stored Page: $storedPage")
    Log.d("Debug", "Curr Page: $currentPage")
    Log.d("Debug", "New Page: $newPage")


    val progress = if (totalChapters > 0) {
      (newPage) * 100 / totalChapters
    } else {
      0
    }
    val status = if (progress == 100) 1 else 0
    Log.d("Debug", "Progress: $progress")

    val userBookData = hashMapOf(
      "user_id" to userId,
      "book_id" to bookId,
      "progress" to progress,
      "status" to status,
      "current_page" to newPage,
      "total_page" to totalChapters,
      "last_seen" to System.currentTimeMillis()
    )

    try {
      if (snapshot.exists()) {
        docRef.update(userBookData as Map<String, Any>).await()
        Log.d("Success", "Data user_books berhasil diperbarui untuk bookId: $bookId")
      } else {
        docRef.set(userBookData).await()
        Log.d("Success", "Data user_books berhasil disimpan untuk bookId: $bookId")
      }
    } catch (e: Exception) {
      Log.e("Error", "Gagal menyimpan atau memperbarui data user_books: $e")
    }
  }
}

