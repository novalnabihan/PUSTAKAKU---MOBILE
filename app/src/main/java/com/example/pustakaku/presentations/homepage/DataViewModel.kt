package com.example.pustakaku.presentations.homepage

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException

import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel: ViewModel(){
  val _genres = mutableStateOf<List<GenreModel>>(emptyList())
  val _books = mutableStateOf<List<BookModel>>(emptyList())
  init {
    getData()
  }

  private fun getData(){
    viewModelScope.launch {
      _genres.value = getGenres()
      _books.value = getBooks()
    }
  }
}

suspend fun getGenres():List<GenreModel> {
  val db = FirebaseFirestore.getInstance()
  var genres = mutableListOf<GenreModel>()

  try {
    val snapshot = db.collection("genres").get().await()
    for (doc in snapshot.documents) {
      doc.toObject(GenreModel::class.java)?.let {
        genres.add(it)
      }
    }
  }catch (e: FirebaseFirestoreException){
    Log.d("error","Error get genres $e")
  }
  return genres
}

suspend fun getBooks():List<BookModel> {
  val db = FirebaseFirestore.getInstance()
  var books = mutableListOf<BookModel>()

  try {
    val snapshot = db.collection("books").get().await()
    for (doc in snapshot.documents) {
      doc.toObject(BookModel::class.java)?.let { book ->
        val bookWithId = book.copy(id = doc.id)
        books.add(bookWithId)
      }
    }
  } catch (e: FirebaseFirestoreException) {
    Log.d("error", "Error getting books: $e")
  }
  return books
}