package com.example.pustakaku.features.homepage

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException

import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class DataViewModel : ViewModel() {
    val _genres = mutableStateOf<List<GenreModel>>(emptyList())
    val _books = mutableStateOf<List<BookModel>>(emptyList())
    val userName = mutableStateOf("User")

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _genres.value = getGenres()
            _books.value = getBooks()
        }
    }


    suspend fun getGenres(): List<GenreModel> {
        val db = FirebaseFirestore.getInstance()
        var genres = mutableListOf<GenreModel>()

        try {
            val snapshot = db.collection("genres").get().await()
            for (doc in snapshot.documents) {
                doc.toObject(GenreModel::class.java)?.let {
                    genres.add(it)
                }
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "Error get genres $e")
        }
        return genres
    }

    suspend fun getBooks(): List<BookModel> {
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

    fun loadUserName(){
      viewModelScope.launch {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null){
          try {
            val db = FirebaseFirestore.getInstance()
            val doc = db.collection("users").document(uid).get().await()
            val name = doc.getString("name")
            if (name != null){
              userName.value = name
            }
          } catch (e: FirebaseFirestoreException){
            Log.d("error", "Error getting user name : $e")
          }
        }
      }
    }


}