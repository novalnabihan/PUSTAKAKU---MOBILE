package com.example.pustakaku.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pustakaku.R

data class Item(
    val image: Int, // Resource ID for the image
    val name: String
)
val recommendItems = listOf(
    Item(image = R.drawable.ic_launcher_foreground, name = "Buku Fiksi"),
    Item(image = R.drawable.ic_launcher_foreground, name = "Buku Biografi"),
    Item(image = R.drawable.ic_launcher_foreground, name = "Thriller"),
)

data class CardData(
    val icon: ImageVector,
    val title: String,
    val backgroundColor: Color
)

val booksCategories = listOf(
    CardData(Icons.Filled.Share, "Mathematics", Color(0xFFD37B4D)),
    CardData(Icons.Filled.Share, "Science", Color(0xFF76C7C0)),
    CardData(Icons.Filled.Share, "History", Color(0xFFD3A04D)),
    CardData(Icons.Filled.Share, "Geography", Color(0xFF4DA7D3)),
    CardData(Icons.Filled.Share, "Literature", Color(0xFFA34DD3)),
    CardData(Icons.Filled.Share, "Art", Color(0xFF4DD38C))
)

data class BookCardData(
    val title: String,
    val author: String,
    val likes: Int,
    val rating: Double
)

val booksPopular = listOf(
    BookCardData(title = "To Kill a Mockingbird", author = "Harper Lee", likes = 245, rating = 4.8),
    BookCardData(title = "1984", author = "George Orwell", likes = 190, rating = 4.6),
    BookCardData(title = "Pride and Prejudice", author = "Jane Austen", likes = 275, rating = 4.7),
    BookCardData(title = "The Great Gatsby", author = "F. Scott Fitzgerald", likes = 150, rating = 4.4),
    BookCardData(title = "Moby-Dick", author = "Herman Melville", likes = 125, rating = 4.1),
    BookCardData(title = "The Catcher in the Rye", author = "J.D. Salinger", likes = 200, rating = 4.5)
)

data class Genre(val description: String, val title: String)